package com.azu.hospital.operation.surgical_list.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.operation.surgical_list.dto.MainSurgicalListDtoMapper;
import com.azu.hospital.operation.surgical_list.entity.MainSurgicalList;
import com.azu.hospital.operation.surgical_list.dao.MainSurgicalListDao;
import com.azu.hospital.operation.surgical_list.dto.MainSurgicalListDto;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class MainSurgicalListGetServices {
    private final MainSurgicalListDao dao;
    private final MainSurgicalListDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public MainSurgicalListGetServices(
            @Qualifier("MainSurgicalListJpa") MainSurgicalListDao dao, MainSurgicalListDtoMapper mapper, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }


    public MainSurgicalListDto getSurgicalNameById(@PathVariable("id") Long id){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        return dao.getSurgicalById(id)
                .map(mapper)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );
    }


    public Page<MainSurgicalListDto> getAllWithFilter(@RequestParam("name") String name, Pageable pageable) {
        Page<MainSurgicalList> listPage = dao.getAllSurgicalList(name, pageable);
        return listPage.map(mapper);
    }

    public Page<MainSurgicalListDto> getAllWithPrice(Pageable pageable) {
        Page<MainSurgicalList> listPage = dao.getAllWithPrice(pageable);
        return listPage.map(mapper);
    }

}
