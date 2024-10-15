package com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList.service;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.newPh.Medicins.dao.MedicinesDao;
import com.azu.hospital.newPh.Medicins.entity.Medicines;
import com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList.dao.MedicinesRequestsListDao;
import com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList.dto.MedicinesRequestsListDto;
import com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList.dto.MedicinesRequestsListDtoMapper;
import com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList.entity.MedicinesRequestsList;
import com.azu.hospital.newPh.MedicinsRequests.dao.MedicinesRequestsDao;
import com.azu.hospital.newPh.MedicinsRequests.dto.MedicinesRequestsDtoMapper;
import com.azu.hospital.newPh.MedicinsRequests.entity.MedicinesRequests;
import com.azu.hospital.newPh.MedicinsRequests.requests.MedicinesRequestsCreate;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class MedicinesRequestsListService {

    private final MedicinesRequestsListDao medicinesRequestsListDao;
    private final MedicinesRequestsDao medicinesRequestsDao;
    private final MedicinesRequestsListDtoMapper medicinesRequestsListDtoMapper;
    private final MedicinesRequestsDtoMapper medicinesRequestsDtoMapper;
    private final MedicinesDao medicinesDao;
    private final PatientDao patientDao;

    private final BaseUserDao baseUserDao;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public MedicinesRequestsListService(MedicinesRequestsListDao medicinesRequestsListDao, MedicinesRequestsDao medicinesRequestsDao, MedicinesRequestsListDtoMapper medicinesRequestsListDtoMapper, MedicinesRequestsDtoMapper medicinesRequestsDtoMapper, MedicinesDao medicinesDao, PatientDao patientDao, BaseUserDao baseUserDao, ExceptionsMessageReturn messageReturn) {
        this.medicinesRequestsListDao = medicinesRequestsListDao;
        this.medicinesRequestsDao = medicinesRequestsDao;
        this.medicinesRequestsListDtoMapper = medicinesRequestsListDtoMapper;
        this.medicinesRequestsDtoMapper = medicinesRequestsDtoMapper;
        this.medicinesDao = medicinesDao;
        this.patientDao = patientDao;
        this.baseUserDao = baseUserDao;
        this.messageReturn = messageReturn;
    }


    @Transactional
    public void addMedicinesRequestsList(Long patientId, List<MedicinesRequestsCreate> requestsCreates) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());

        MedicinesRequestsList list = new MedicinesRequestsList();
        List<MedicinesRequests> medicinesRequests = new ArrayList<>();

        Patient patient = patientDao.getPatientById(patientId).orElseThrow(
                () -> new RuntimeException(
                        messages.getString("resourceNotFound")
                )
        );

        list.setPatient(patient);


        MedicinesRequestsList savedList = medicinesRequestsListDao.addMedicinesRequestsList(list);

        for (MedicinesRequestsCreate request : requestsCreates) {
            Medicines medicines = medicinesDao.getMedicineById(request.medicinesId()).orElseThrow(
                    () -> new RuntimeException(
                            messages.getString("resourceNotFound")
                    )
            );
            if (!patient.getIsHasMedicalHistory())
                throw new BadRequestException(
                        messages.getString("medicalHistory")+" "+patientId
                );

            MedicinesRequests hander = new MedicinesRequests();
            hander.setMedicines(medicines);
            hander.setQuantity(request.quantity());
            hander.setTimesDay(request.timesDay());
            hander.setTimesServing(request.timesServing());
            hander.setNote(request.note());
            hander.setPatient(patient);
            hander.setRoa(request.roa());
            hander.setType(request.type());
            hander.setDoes(request.does());
            hander.setRequestStatus(UnitInventoryRequestEnum.Waitting);
            medicinesRequests.add(hander);

        }
        List<MedicinesRequests> savedMedicinesRequests = medicinesRequestsDao.createMedicinesRequests(medicinesRequests);

        savedList.setMedicinesRequests(savedMedicinesRequests);

    }


    public MedicinesRequestsListDto getMedicinesRequestsListById(Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        return medicinesRequestsListDao.getMedicinesRequestsListById(id)
                .map(medicinesRequestsListDtoMapper).orElseThrow(
                        () -> new RuntimeException(
                                messages.getString("resourceNotFound")
                        )
                );

    }

    public Page<MedicinesRequestsListDto> getMedicinesRequestsListByPatientId(Long patientId, Pageable pageable) {
        return medicinesRequestsListDao.getAllByPatientId(patientId,pageable)
                .map(medicinesRequestsListDtoMapper);
    }

    public Page<MedicinesRequestsListDto> getMedicinesRequestsListAllWithFilter(String patientName, Pageable pageable) {
        return medicinesRequestsListDao.getMedicinesRequestsListAllWithFilter(patientName,pageable)
                .map(medicinesRequestsListDtoMapper);
    }

    public Page<MedicinesRequestsListDto> getMedicinesRequestsListAccordingToUpdatedAt(Pageable pageable) {
        return medicinesRequestsListDao.getMedicinesRequestsListAccordingToUpdatedAt(pageable)
                .map(medicinesRequestsListDtoMapper);
    }



}
