package com.azu.hospital.ph.StockMangment.otherConsumablesList.service;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.accounting.all_item_expanse.other_consumbles.service.IPatientOtherConsumablesResultTableService;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.dao.OtherConsumablesListDao;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.entity.OtherConsumablesList;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.dao.OtherConsumablesDao;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.entity.OtherConsumables;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.request.OtherConsumablesCreateRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class OtherConsumablesListService {
    private final OtherConsumablesListDao otherConsumablesListDao;
    private final ExceptionsMessageReturn messageReturn;

    private final OtherConsumablesDao otherConsumablesDao;

    private final IPatientOtherConsumablesResultTableService iPatientOtherConsumablesResultTableService;
    private final PatientDao patientDao;

    public OtherConsumablesListService(OtherConsumablesListDao otherConsumablesListDao, ExceptionsMessageReturn messageReturn, OtherConsumablesDao otherConsumablesDao, IPatientOtherConsumablesResultTableService iPatientOtherConsumablesResultTableService, PatientDao patientDao) {
        this.otherConsumablesListDao = otherConsumablesListDao;
        this.messageReturn = messageReturn;
        this.otherConsumablesDao = otherConsumablesDao;
        this.iPatientOtherConsumablesResultTableService = iPatientOtherConsumablesResultTableService;
        this.patientDao = patientDao;
    }


    public void createOtherConsumablesList(Long patientId, List<OtherConsumablesCreateRequest> registrationsRequest) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );

        OtherConsumablesList list = new OtherConsumablesList();

        List<OtherConsumables> listOtherConsumables = new ArrayList<>();

        list.setPatient(patient);

        OtherConsumablesList otherConsumablesList = otherConsumablesListDao.addOtherConsumables(list);

        for (OtherConsumablesCreateRequest request : registrationsRequest) {
            OtherConsumables otherConsumables = new OtherConsumables();
            otherConsumables.setNote(request.note());
            otherConsumables.setName(request.name());
            otherConsumables.setCount(request.count());
            otherConsumables.setPatient(patient);
            otherConsumables.setOtherConsumablesList(otherConsumablesList);
            listOtherConsumables.add(otherConsumables);
            iPatientOtherConsumablesResultTableService.addPatientOtherConsumablesResultTable(patientId);
        }
        otherConsumablesDao.createList(listOtherConsumables);

    }

    public void addOtherConsumablesToList(Long listId, List<OtherConsumablesCreateRequest> request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        OtherConsumablesList list = otherConsumablesListDao.getOtherConsumablesList(listId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );
        for (OtherConsumablesCreateRequest otherConsumablesCreateRequest : request) {
            OtherConsumables otherConsumables = new OtherConsumables(
                    otherConsumablesCreateRequest.name(),
                    otherConsumablesCreateRequest.count(),
                    otherConsumablesCreateRequest.note()
            );
            otherConsumables.setPatient(list.getPatient());
            list.getOtherConsumables().add(otherConsumables);
            iPatientOtherConsumablesResultTableService.addPatientOtherConsumablesResultTable(list.getPatient().getId());
        }
        otherConsumablesListDao.addOtherConsumables(list);
        otherConsumablesDao.createList(list.getOtherConsumables());


    }


}
