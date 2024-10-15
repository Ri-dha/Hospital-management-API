package com.azu.hospital.bulding.ward.wardBed.service;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.bulding.floor.dao.FloorDao;
import com.azu.hospital.bulding.ward.wardBed.dao.BedDao;
import com.azu.hospital.bulding.ward.wardBed.dto.BedDto;
import com.azu.hospital.bulding.ward.wardBed.dto.BedDtoMapper;
import com.azu.hospital.bulding.ward.wardBed.entity.Bed;
import com.azu.hospital.bulding.ward.wardBed.request.CreateBedRequest;
import com.azu.hospital.bulding.ward.dao.WardDao;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BedService {

    private final BedDao bedDao;
    private final WardDao wardDao;
    private final FloorDao floorDao;


    private final PatientDao patientDao;

    private final BedDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public BedService(
            @Qualifier("bedRepo") BedDao bedDao,
            @Qualifier("wardRepo") WardDao wardDao, FloorDao floorDao,
            @Qualifier("patientRepo") PatientDao patientDao,
            BedDtoMapper mapper, ExceptionsMessageReturn messageReturn
    ) {
        this.bedDao = bedDao;
        this.wardDao = wardDao;
        this.floorDao = floorDao;
        this.patientDao = patientDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }

    public Page<BedDto> getAllBedByWard(Long id , Boolean isReserved , Pageable pageable){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

         wardDao.findWardById(id).orElseThrow(
                ()->new ResourceNotFoundException(
                        messages.getString("resourceNotFound") + id
                )
        );

        if (isReserved){
            List<BedDto> dtoList =  bedDao.getAllBedByWardIdAndIsReserved(id , pageable)
                    .stream()
                    .map(mapper::toDto)
                    .toList();
            return new PageImpl<>(dtoList, pageable, pageable.getPageSize());
        }

        List<BedDto> dtoList = bedDao.getAllBedByWardIdAndIsNotReserved(id , pageable)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, pageable.getPageSize());

    }

    public void createNewBed(@Valid @ModelAttribute  CreateBedRequest request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Ward ward = wardDao.findWardById(request.getWardId()).orElseThrow(
                ()->new ResourceNotFoundException(
                        messages.getString("resourceNotFound") + request.getWardId()
                )
        );

        Bed bed = new Bed(request.getBedNumber());
        bed.setWard(ward);

        ward.setBeds(List.of(bed));
         bedDao.createNewBed(bed);

    }

    // TODO: 11/6/2023 use dto class for return instead of map
    public Map<String , Integer> getBedStatus(Long wardId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        wardDao.findWardById(wardId).orElseThrow(
                ()->new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        Map<String , Integer> data = new LinkedHashMap<>();
        data.put("totalBed" , bedDao.countAllBed(wardId));
        data.put("reservedBed" , bedDao.countOfBedReserved(wardId));
        data.put("notReservedBed" , bedDao.countOfBedNotReserved(wardId));
        return data;

        // TODO: 11/6/2023 use status for showing is bed reserved or not
    }


    public void changePatientWard(Long patientId , Long bedId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Patient patient = patientDao.getPatientById(patientId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

       Bed bed = bedDao.getBedById(bedId).orElseThrow(
               () -> new ResourceNotFoundException(
                          messages.getString("resourceNotFound")
               )
       );

       patient.setBed(bed);
       patient.setWard(bed.getWard());

       patientDao.updatePatient(patient);
    }

    public void updateBed(Long bedId , String bedNumber){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Bed bed = bedDao.getBedById(bedId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if (Objects.equals(bedNumber, bed.getBedNumber())){
            throw new BadRequestException(
                    messages.getString("noChanges")
            );
        }

        bed.setBedNumber(bedNumber);

        bedDao.updateBed(bed);
    }


    public void blockBedById(Long bedId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Bed bed = bedDao.getBedById(bedId)
                .orElseThrow(
                        ()-> new ResourceNotFoundException(
                              messages.getString("resourceNotFound")
                        )
                );
        bed.setIsBlock(true);
        bedDao.updateBed(bed);
    }

    public void unblockBedById(Long bedId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Bed bed = bedDao.getBedById(bedId)
                .orElseThrow(
                        ()-> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );
        bed.setIsBlock(false);
        bedDao.updateBed(bed);
    }


    public Page<BedDto> getAllBedsInFloor(Long floorId , Pageable pageable){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        floorDao.selectFloorById(floorId).orElseThrow(
                ()->new ResourceNotFoundException(
                        messages.getString("floorNotFound")
                )
        );
        List<BedDto> dtoList = bedDao.getBedByFloorId(floorId , pageable)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, pageable.getPageSize());
    }


    public BedDto getBedById(Long bedId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        return bedDao.getBedByIdAndWardIdAndBedNotReserved(bedId)
                .map(mapper::toDto)
                .orElseThrow(
                        ()->new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );
    }

}
