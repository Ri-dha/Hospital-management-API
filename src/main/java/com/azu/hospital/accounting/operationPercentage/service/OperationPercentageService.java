package com.azu.hospital.accounting.operationPercentage.service;


import com.azu.hospital.accounting.operationPercentage.dao.OperationPercentageDao;
import com.azu.hospital.accounting.operationPercentage.dto.OperationPercentageDto;
import com.azu.hospital.accounting.operationPercentage.dto.OperationPercentageDtoMapper;
import com.azu.hospital.accounting.operationPercentage.entity.OperationPercentage;
import com.azu.hospital.accounting.operationPercentage.request.OperationPercentageCreateRequest;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.operation.dao.OperationDao;
import com.azu.hospital.operation.surgical_list.dao.MainSurgicalListDao;
import com.azu.hospital.operation.surgical_list.entity.MainSurgicalList;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class OperationPercentageService {
    private final OperationPercentageDao dao;
    private final MainSurgicalListDao surgicalListDao;
    private final OperationDao operationDao;
    private final ExceptionsMessageReturn messageReturn;
    private final OperationPercentageDtoMapper mapper;

    public OperationPercentageService(OperationPercentageDao dao, MainSurgicalListDao surgicalListDao, OperationDao operationDao, ExceptionsMessageReturn messageReturn, OperationPercentageDtoMapper mapper) {
        this.dao = dao;
        this.surgicalListDao = surgicalListDao;
        this.operationDao = operationDao;
        this.messageReturn = messageReturn;
        this.mapper = mapper;
    }

    public void createNewOperationPercentage(Long operationId, OperationPercentageCreateRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        MainSurgicalList surgicalList = surgicalListDao.getSurgicalById(operationId).orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );
        if(surgicalList.getPrice() == null){
            throw new RuntimeException(
                    messages.getString("priceNotAvailable")
            );
        }
        OperationPercentage operationPercentage= new OperationPercentage(
                        request.doctorPercentage(),
                        request.anesthetistDoctorPercentage(),
                        request.permanentPercentage(),
                        request.pharmacistPercentage(),
                        request.nursePercentage(),
                        request.anesthetistPercentage(),
                        request.hospitalPercentage()
        );
        BigDecimal totalPercentage = request.doctorPercentage()
                .add(request.anesthetistDoctorPercentage())
                .add(request.permanentPercentage())
                .add(request.pharmacistPercentage())
                .add(request.nursePercentage())
                .add(request.anesthetistPercentage());

        if(totalPercentage.compareTo(surgicalList.getPrice())>0){
            throw new BadRequestException(
                    messages.getString("priceNotAvailable")
            );
        }
        operationPercentage.setSurgicalList(surgicalList);
        dao.createNewOperationPercentage(operationPercentage);
    }

    public OperationPercentageDto getOperationPercentageById(Long id) {
        return dao.getOperationPercentageById(id)
                .stream()
                .map(mapper)
                .findFirst().orElse(null);

    }

    public void updateOperationPercentage(Long id, OperationPercentageCreateRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        OperationPercentage operationPercentage = dao.getOperationPercentageById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Operation percentage not found")
        );
       boolean changes= false;
            if(request.doctorPercentage()!=null){
              operationPercentage.setDoctorPercentage(request.doctorPercentage());
              changes=true;
             }
            if(request.anesthetistDoctorPercentage()!=null){
                operationPercentage.setAnesthetistDoctorPercentage(request.anesthetistDoctorPercentage());
                changes=true;
            }
            if(request.permanentPercentage()!=null){
                operationPercentage.setPermanentPercentage(request.permanentPercentage());
                changes=true;
            }
            if(request.pharmacistPercentage()!=null){
                operationPercentage.setPharmacistPercentage(request.pharmacistPercentage());
                changes=true;
            }
            if(request.nursePercentage()!=null){
                operationPercentage.setNursePercentage(request.nursePercentage());
                changes=true;
            }
            if(request.anesthetistPercentage()!=null){
                operationPercentage.setAnesthetistPercentage(request.anesthetistPercentage());
                changes=true;
            }
            if(!changes){
                messages.getString("noChanges");
            }
            dao.updateOperationPercentage(operationPercentage);
    }

    public void deleteOperationPercentage(Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        OperationPercentage operationPercentage = dao.getOperationPercentageById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );
        dao.deleteOperationPercentage(operationPercentage);
    }

    public Page<OperationPercentageDto> getAllOperationPercentages(Pageable pageable) {
       List<OperationPercentageDto> operationPercentageDto= dao.getAllOperationPercentages(pageable)
               .stream()
               .map(mapper).toList();

       return new PageImpl<>(operationPercentageDto,pageable,operationPercentageDto.size());
    }
}
