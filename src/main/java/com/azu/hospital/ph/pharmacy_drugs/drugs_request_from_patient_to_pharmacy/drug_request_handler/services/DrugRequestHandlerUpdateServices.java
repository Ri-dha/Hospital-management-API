package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dao.DrugRequestHandlerDao;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.entity.DrugRequestHandler;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.request.DrugRequestHandlerUpdateRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class DrugRequestHandlerUpdateServices {
    private final ExceptionsMessageReturn messageReturn;

    private final DrugRequestHandlerDao handlerDao;
    @Autowired
    public DrugRequestHandlerUpdateServices(ExceptionsMessageReturn messageReturn, DrugRequestHandlerDao handlerDao) {
        this.messageReturn = messageReturn;
        this.handlerDao = handlerDao;
    }

    public void updateDrugRequestHandler(Long id , DrugRequestHandlerUpdateRequest request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        DrugRequestHandler drugRequestHandler = handlerDao.getRequestById(id)
                .orElseThrow(
                        ()->new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );

        boolean changes = false;
        if(request.quantity() != null){
            drugRequestHandler.setQuantity(request.quantity());
            changes=true;
        } if(request.timesDay() != null){
            drugRequestHandler.setTimesDay(request.timesDay());
            changes=true;
        }if(request.drugName() != null){
            drugRequestHandler.setDrugName(request.drugName());
            changes=true;
        }if(request.timesServing() != null){
        drugRequestHandler.setTimesServing(request.timesServing());
        changes=true;
        }if(request.does() != null){
            drugRequestHandler.setDoes(request.does());
            changes=true;
        }if(request.roa() != null){
            drugRequestHandler.setRoa(request.roa());
            changes=true;
        }if(request.type() != null){
            drugRequestHandler.setType(request.type());
            changes=true;
        }if(request.note() != null){
            drugRequestHandler.setNote(request.note());
            changes=true;
        }if(!changes){
            throw new ResourceNotFoundException(
                    messages.getString("resourceNotFound")
            );
        }
        handlerDao.updateDrugRequestHandler(drugRequestHandler);

    }

}
