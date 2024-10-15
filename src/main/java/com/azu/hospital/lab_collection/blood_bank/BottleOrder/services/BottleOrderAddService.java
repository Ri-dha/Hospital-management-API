package com.azu.hospital.lab_collection.blood_bank.BottleOrder.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.lab_collection.blood_bank.Bottle.dao.BottleDao;
import com.azu.hospital.lab_collection.blood_bank.Bottle.entity.Bottle;
import com.azu.hospital.lab_collection.blood_bank.BottleOrder.dao.BottleOrderDao;
import com.azu.hospital.lab_collection.blood_bank.BottleOrder.dto.BottleOrderDtoMapper;
import com.azu.hospital.lab_collection.blood_bank.BottleOrder.entity.BottleOrder;
import com.azu.hospital.lab_collection.blood_bank.BottleOrder.request.CreateBottleOrder;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.bottles.BloodGroupEnum;
import com.azu.hospital.utils.enums.bottles.BottleStateEnum;
import com.azu.hospital.utils.enums.bottles.BottleTypeEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class BottleOrderAddService {

    private final BottleOrderDao bottleOrderDao;

    private final PatientDao patientDao;
    private final BottleOrderDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;

    private final BottleDao bottleDao;

    public BottleOrderAddService(
            @Qualifier("bottleOrderRepo") BottleOrderDao bottleOrderDao,
            @Qualifier("patientRepo") PatientDao patientDao,
            BottleOrderDtoMapper mapper, ExceptionsMessageReturn messageReturn, BottleDao bottleDao
    ) {
        this.bottleOrderDao = bottleOrderDao;
        this.patientDao = patientDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
        this.bottleDao = bottleDao;
    }


    public void createBottleOrder(CreateBottleOrder request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Patient patient = patientDao.getPatientById(request.patientId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("patientNotFound") + " " + request.patientId()
                )
        );
        if (!isBottleCountValid(request.bottleType(), request.bloodGroup(), request.count())) {
            throw new BadRequestException(
                    messages.getString("quantityNotAvailable"));
        }

        BottleOrder bottleOrder = new BottleOrder(
                request.isReserved(),
                request.note(),
                BottleStateEnum.NoArchive,
                request.bottleType(),
                request.bloodGroup(),
                request.count()
        );
        bottleOrder.setPatient(patient);
        bottleOrderDao.createNewOrder(bottleOrder);
    }


    private boolean isBottleCountValid(BottleTypeEnum bottleType, BloodGroupEnum bloodGroup, int requestedCount) {
        if (bottleType == BottleTypeEnum.BLOOD || bottleType == BottleTypeEnum.RBC) {
            long availableCount = bottleDao.countAllByStateAndBottleType(bloodGroup,bottleType);
            return requestedCount <= availableCount;
        } else {
            long availableCount = bottleDao.countByType(bottleType);
            return requestedCount <= availableCount;
        }
    }

}



