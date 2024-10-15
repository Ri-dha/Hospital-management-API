package com.azu.hospital.patient_expances.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.accounting.all_item_expanse.consumables.service.IPatientsExpansesResultTableService;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.patient_expances.dao.PatientExpanseDao;
import com.azu.hospital.patient_expances.dao.PatientExpanseListDao;
import com.azu.hospital.patient_expances.entity.PatientExpanse;
import com.azu.hospital.patient_expances.entity.PatientExpanseList;
import com.azu.hospital.patient_expances.request.PatientExpanseRequest;
import com.azu.hospital.ph.StockMangment.Consumbles.dao.ConsumableDao;
import com.azu.hospital.ph.StockMangment.Consumbles.entity.Consumables;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Generic.GenericBaseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class PatientExpanseAddServices extends GenericBaseService {

    private final PatientExpanseDao dao;
    private final PatientDao patientDao;
    private final BaseUserDao userDao;
    private final PatientExpanseListDao expanseListDao;
    private final ExceptionsMessageReturn messageReturn;

    private final ConsumableDao consumableDao;

    private final IPatientsExpansesResultTableService patientsExpansesResultTableService;

    @Autowired
    public PatientExpanseAddServices(
            PatientExpanseDao dao,
            PatientDao patientDao,
            BaseUserDao userDao,
            PatientExpanseListDao expanseListDao,
            ExceptionsMessageReturn messageReturn,
            ConsumableDao consumableDao,
            IPatientsExpansesResultTableService patientsExpansesResultTableService,
            JwtService jwtService,
            HttpServletRequest httpServletRequest) {
        super(jwtService, httpServletRequest);
        this.dao = dao;
        this.patientDao = patientDao;
        this.userDao = userDao;
        this.expanseListDao = expanseListDao;
        this.messageReturn = messageReturn;
        this.consumableDao = consumableDao;
        this.patientsExpansesResultTableService = patientsExpansesResultTableService;
    }

    @Transactional
    public void addPatientList(Long patientId, List<PatientExpanseRequest> requests) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("patientNotFound") + patientId
                        )
                );

        PatientExpanseList list = new PatientExpanseList();

        List<PatientExpanse> expanseList = new ArrayList<>();

        list.setPatient(patient);

        list.setUser(userDao.findById(authId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("userNotFound") + authId()
                        )));


        for (PatientExpanseRequest request : requests) {
            Consumables consumables = consumableDao.findConsumableById(request.getConsumableId())
                    .orElseThrow(() -> new ResourceNotFoundException(messages.getString("resourceNotFound") + request.getConsumableId()));

            PatientExpanse expanse = new PatientExpanse(
                    request.getItemName(),
                    request.getItemCount(),
                    request.getNote()
            );

            expanse.setPatient(list.getPatient());
            expanse.setConsumables(consumables);
            expanseList.add(expanse);

        }

        list.setPatientExpanse(expanseList);
        dao.addPatientExpanseList(expanseList);

        expanseListDao.addPatientExpanseList(list);
        patientsExpansesResultTableService.addPatientExpansesExpanseResultTable(patientId);


    }

    public void addPatientExpanseToList(Long listId, List<PatientExpanseRequest> request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        PatientExpanseList list = expanseListDao.getPatientExpanseById(listId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound") + listId
                        )
                );

        for (PatientExpanseRequest expanseRequest : request) {
            Consumables consumables = consumableDao.findConsumableById(expanseRequest.getConsumableId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException(
                                    messages.getString("resourceNotFound") + expanseRequest.getConsumableId()
                            )
                    );
            PatientExpanse expanse = new PatientExpanse(
                    expanseRequest.getItemName(),
                    expanseRequest.getItemCount(),
                    expanseRequest.getNote()
            );
            expanse.setPatient(list.getPatient());
            expanse.setConsumables(consumables);
            list.getPatientExpanse().add(expanse);
            dao.addPatientExpanseList(list.getPatientExpanse());
            expanse.setConsumables(consumables);
        }
        expanseListDao.updateList(list);
    }
}
