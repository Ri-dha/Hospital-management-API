package com.azu.hospital.hospital_info.service;


import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.hospital_info.dao.HospitalInfoDao;
import com.azu.hospital.hospital_info.dto.HospitalInfoDto;
import com.azu.hospital.hospital_info.dto.HospitalInfoDtoMapper;
import com.azu.hospital.hospital_info.entity.HospitalInfo;
import com.azu.hospital.hospital_info.request.HospitalInfoRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class HospitalInfoService {

    private final HospitalInfoDao dao;
    private final HospitalInfoDtoMapper mapper;
    private final ImageService imageService;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public HospitalInfoService(HospitalInfoDao dao, HospitalInfoDtoMapper mapper, ImageService imageService, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.mapper = mapper;
        this.imageService = imageService;
        this.messageReturn = messageReturn;
    }

    public void createHospitalInfo(HospitalInfoRequest request) throws IOException {
        HospitalInfo hospitalInfo = new HospitalInfo(
                request.getName()
        );
        if(request.getImage() != null){
            String image = imageService.saveImage(request.getImage());
            hospitalInfo.setImage(image);
        }
        dao.createHospitalInfo(hospitalInfo);
    }

    public void updateHospitalInfo(Long id, HospitalInfoRequest request) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        HospitalInfo hospitalInfo = dao.getHospitalInfoById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("hospitalInfoNotFound") + " " + id
                )
        );
        boolean changes = false;
        if (request.getName() != null) {
            hospitalInfo.setName(request.getName());
            changes = true;
        }
        if (request.getImage() != null) {
            hospitalInfo.setImage(imageService.saveImage(request.getImage()));
            changes = true;
        }
        if (!changes) {
            throw new RuntimeException("No changes found");
        }
        dao.updateHospitalInfo(hospitalInfo);
    }

    public HospitalInfoDto getHospitalInfoById(Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        return dao.getHospitalInfoById(id)
                .stream()
                .map(mapper)

                .findFirst().orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("hospitalInfoNotFound") + " " + id
                        )
                );

    }


}


