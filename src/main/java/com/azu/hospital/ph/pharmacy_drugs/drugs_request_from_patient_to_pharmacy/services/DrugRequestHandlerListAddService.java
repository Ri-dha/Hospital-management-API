package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.main_data_store.drugs_item.dao.DrugItemDao;
import com.azu.hospital.ph.main_data_store.drugs_item.entity.DrugsItem;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.dao.DrugRequestHandlerListDao;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dao.DrugRequestHandlerDao;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.entity.DrugRequestHandler;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.request.DrugRequestHandlerRegistrationsRequest;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.entity.DrugRequestHandlerList;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service

public class DrugRequestHandlerListAddService {

    private final DrugRequestHandlerListDao dao;

    private final DrugRequestHandlerDao requestHandlerDao;

    private final DrugItemDao drugItemDao;

    private final PatientDao patientDao;

    private final BaseUserDao baseUserDao;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public DrugRequestHandlerListAddService(DrugRequestHandlerListDao dao, DrugRequestHandlerDao requestHandlerDao, DrugItemDao drugItemDao, PatientDao patientDao, BaseUserDao baseUserDao, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.requestHandlerDao = requestHandlerDao;
        this.drugItemDao = drugItemDao;
        this.patientDao = patientDao;
        this.baseUserDao = baseUserDao;
        this.messageReturn = messageReturn;
    }

    @Transactional
    public void addDrugRequestList(Long patientId, List<DrugRequestHandlerRegistrationsRequest> registrationsRequest) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());

        DrugRequestHandlerList list = new DrugRequestHandlerList();

        List<DrugRequestHandler> handlerList = new ArrayList<>();


//        BaseUser user = baseUserDao.findById(registrationsRequest.get(0).signaturesId())
//                .orElseThrow(
//                        () -> new UsernameNotFoundException(
//                                messages.getString("resourceNotFound")+registrationsRequest.get(0).signaturesId()
//                        )
//                );
        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")+patientId
                        )
                );

        list.setPatient(patient);

        DrugRequestHandlerList drugRequestHandlerList = dao.addDrugRequestHandlerList(list);


        for (DrugRequestHandlerRegistrationsRequest request : registrationsRequest) {
//            DrugsItem table = drugItemDao.selectDrudById(request.drugId())
//                    .orElseThrow(
//                            () -> new ResourceNotFoundException(
//                                    messages.getString("resourceNotFound")+request.drugId()
//                            )
//                    );

            if (!patient.getIsHasMedicalHistory())
                throw new BadRequestException(
                        messages.getString("medicalHistory")+patientId
                );

            DrugRequestHandler handler = new DrugRequestHandler();
            handler.setDrugId(request.drugId());
            handler.setDrugName(request.drugName());
            handler.setNote(request.note());
            handler.setTimesDay(request.timesDay());
            handler.setTimesServing(request.timesServing());
            handler.setQuantity(request.quantity());
//            handler.setDrugsItems(table);
//            handler.setSignature(user);
            handler.setPatient(patient);
            handler.setDoes(request.does());
            handler.setRoa(request.roa());
            handler.setType(request.type());
            handler.setRequestStatus(UnitInventoryRequestEnum.Waitting);
            handlerList.add(handler);
        }

        List<DrugRequestHandler> handler = requestHandlerDao.addNewDrugRequest(handlerList);
        drugRequestHandlerList.setDrugRequestHandlers(handler);


    }
}
