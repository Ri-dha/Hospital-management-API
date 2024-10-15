package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.main_data_store.drugs_item.dao.DrugItemDao;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.dao.DrugRequestHandlerListDao;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dao.DrugRequestHandlerDao;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.entity.DrugRequestHandler;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.request.DrugRequestHandlerRegistrationsRequest;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.entity.DrugRequestHandlerList;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


@Service
public class DrugRequestHandlerAddServices {

    private final DrugRequestHandlerDao handlerDao;
    private final BaseUserDao baseUserDao;
    private final PatientDao patientDao;
    private final DrugItemDao drugItemDao;
    private final DrugRequestHandlerListDao drugRequestHandlerListDao;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public DrugRequestHandlerAddServices(
            @Qualifier("DrugRequestHandlerJpa") DrugRequestHandlerDao handlerDao,
            BaseUserDao baseUserDao, PatientDao patientDao,
            DrugItemDao drugItemDao, DrugRequestHandlerListDao drugRequestHandlerListDao, ExceptionsMessageReturn messageReturn) {
        this.handlerDao = handlerDao;
        this.baseUserDao = baseUserDao;
        this.patientDao = patientDao;
        this.drugItemDao = drugItemDao;
        this.drugRequestHandlerListDao = drugRequestHandlerListDao;
        this.messageReturn = messageReturn;
    }

    public void addRequestHandler(Long listId,List<DrugRequestHandlerRegistrationsRequest> registrationsRequest){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());

        DrugRequestHandlerList list = drugRequestHandlerListDao.getDrugRequestHandlerListById(listId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                ));

        List<DrugRequestHandler> handlerList = list.getDrugRequestHandlers();

//        BaseUser user = baseUserDao.findById(registrationsRequest.get(0).signaturesId())
//                .orElseThrow(
//                        ()-> new UsernameNotFoundException(
//                                messages.getString("resourceNotFound")+registrationsRequest.get(0).signaturesId()
//                        )
//                );
        Patient patient = patientDao.getPatientById(registrationsRequest.get(0).patientId())
                .orElseThrow(
                        ()-> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")+registrationsRequest.get(0).patientId()
                        )
                );

        for (DrugRequestHandlerRegistrationsRequest request : registrationsRequest){
//            DrugsItem table = drugItemDao.selectDrudById(request.drugId())
//                    .orElseThrow(
//                            ()-> new ResourceNotFoundException(
//                                    messages.getString("resourceNotFound")+request.drugId()
//                            )
//                    );

            if (!patient.getIsHasMedicalHistory())
                throw new BadRequestException(
                        messages.getString("patientHasNoMedicalHistory")
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
            handlerDao.addNewDrugRequest(handlerList);
        }

        list.setDrugRequestHandlers(handlerList);
        drugRequestHandlerListDao.addDrugRequestHandlerList(list);
    }



}
