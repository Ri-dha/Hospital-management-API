package com.azu.hospital.lab_collection.test_list.services;

import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.lab_collection.test_list.dao.LabListDao;
import com.azu.hospital.lab_collection.test_list.dto.LabListDto;
import com.azu.hospital.lab_collection.test_list.dto.LabListDtoMapper;
import com.azu.hospital.lab_collection.test_list.entity.LabList;
import com.azu.hospital.lab_collection.test_list.request.CreateLabListRequest;
import com.azu.hospital.lab_collection.test_list.request.LabListDataRequest;
import com.azu.hospital.lab_collection.test_list.request.UpdateLabListRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class LabListService {

    private final LabListDtoMapper mapper;

    private final LabListDao labListDao;
    private final ExceptionsMessageReturn messageReturn;


    public LabListService(
            LabListDtoMapper mapper,
            @Qualifier("labListRepo") LabListDao labListDao, ExceptionsMessageReturn messageReturn) {
        this.mapper = mapper;
        this.labListDao = labListDao;
        this.messageReturn = messageReturn;
    }

    public void createManyLabList(CreateLabListRequest labListRequest){

        List<LabList> labLists = new ArrayList<>();

        for (LabListDataRequest labListDataRequest: labListRequest.labList()){
             LabList labList = new LabList(labListDataRequest.name());
             labList.setIqdPrice(labListDataRequest.iqdPrice());
             labList.setUsdPrice(labListDataRequest.usdPrice());
             labLists.add(labList);
        }
         labListDao.createManyLabList(labLists);

    }



    public Page<LabListDto> getLabList(String testName, Pageable pageable){
        return labListDao.getAllLabList(testName,pageable).map(mapper::toDto);
    }



    public void updateLabList(Long labId ,UpdateLabListRequest request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        LabList labList = labListDao.findLabListById(labId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        boolean change = false;

        if (request.name() != null && !labList.getLabName().equalsIgnoreCase(request.name())){
            labList.setLabName(request.name());
            change = true;
        }



        if (request.iqdPrice() != null  && labList.getIqdPrice() != null && request.iqdPrice().compareTo(labList.getIqdPrice()) != 0) {
            labList.setIqdPrice(request.iqdPrice());
            change = true;
        }else if (request.iqdPrice() != null && labList.getIqdPrice() == null){
            labList.setIqdPrice(request.iqdPrice());
            change = true;
        }


        if (request.usdPrice() != null && labList.getUsdPrice() != null && request.usdPrice().compareTo(labList.getUsdPrice()) != 0) {
            labList.setUsdPrice(request.usdPrice());
            change = true;
        }else if (request.usdPrice() != null && labList.getUsdPrice() == null){
            labList.setUsdPrice(request.usdPrice());
            change = true;
        }

        if (!change){
            throw new BadRequestException(
                    messages.getString("noChanges")
            );
        }

        labListDao.updateLabList(labList);
    }


    public void deleteById(Long id){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        labListDao.findLabListById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        labListDao.deleteLabListById(id);
    }



}
