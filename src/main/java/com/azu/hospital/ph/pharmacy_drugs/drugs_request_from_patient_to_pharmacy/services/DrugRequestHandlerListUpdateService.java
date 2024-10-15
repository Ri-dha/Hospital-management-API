package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.dao.DrugRequestHandlerListDao;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dao.DrugRequestHandlerDao;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.entity.DrugRequestHandler;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.request.DrugRequestHandlerUpdateRequest;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.entity.DrugRequestHandlerList;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.request.DrugRequestHandlerListUpdateRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DrugRequestHandlerListUpdateService {
    private final DrugRequestHandlerListDao dao;
    private final DrugRequestHandlerDao handlerDao;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public DrugRequestHandlerListUpdateService(DrugRequestHandlerListDao dao,
                                               DrugRequestHandlerDao handlerDao, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.handlerDao = handlerDao;
        this.messageReturn = messageReturn;
    }

    public void updateDrugRequestHandlerList(Long listId, DrugRequestHandlerListUpdateRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        DrugRequestHandlerList drugRequestHandlerList = dao.getDrugRequestHandlerListById(listId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );
        List<DrugRequestHandler> changedItems = new ArrayList<>();
        for (DrugRequestHandlerUpdateRequest update : request.request()) {
            DrugRequestHandler handler = handlerDao.getRequestById(update.requestId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            messages.getString("resourceNotFound")
                    ));

            Map<String, String> changesMap = new LinkedHashMap<>();
            boolean changes = false;

                if (update.quantity() != null && !Objects.equals(update.quantity() , handler.getQuantity()) ) {
                if (handler.getCrashUpdate() != null) {
                    changesMap.put("quantity" , String.valueOf(handler.getQuantity()));
                }
                handler.setQuantity(update.quantity());
                changes = true;
            }
            if (update.timesDay() != null && !Objects.equals(update.timesDay() , handler.getTimesDay()) ) {
                if (handler.getCrashUpdate() != null) {
                    changesMap.put("timesDay" ,  handler.getTimesDay());
                }
                handler.setTimesDay(update.timesDay());
                changes = true;

            }

            if (update.type() != null && !Objects.equals(update.type() , handler.getType()) ) {
                if (handler.getCrashUpdate() != null) {
                    changesMap.put("type" , handler.getType().toString());
                }
                handler.setType(update.type());
                changes = true;

            }
            if (update.timesServing() != null && !Objects.equals(update.timesServing() , handler.getTimesServing()) ) {
                if (handler.getCrashUpdate() != null) {
                    changesMap.put("timesServing" ,  handler.getTimesServing());
                }
                handler.setTimesServing(update.timesServing());
                changes = true;
            }
            if (update.roa() != null && !Objects.equals(update.roa() , handler.getRoa())) {
                if (handler.getCrashUpdate() != null) {
                    changesMap.put("roa" ,  handler.getRoa());
                }
                handler.setRoa(update.roa());
                changes = true;
            }
            if (update.does() != null && !Objects.equals(update.does() , handler.getDoes()) ) {
                if (handler.getCrashUpdate() != null) {
                    changesMap.put("does" ,  handler.getDoes());
                }
                handler.setDoes(update.does());
                changes = true;
            }
            if (update.note() != null && !Objects.equals(update.note(), handler.getNote())) {
                if (handler.getCrashUpdate() != null) {
                    changesMap.put("note" ,  handler.getNote());
                }
                handler.setNote(update.note());
                changes = true;
            } if (update.drugName() != null && !Objects.equals(update.drugName(), handler.getDrugName())) {
                if (handler.getCrashUpdate() != null) {
                    changesMap.put("drugName" ,  handler.getDrugName());
                }
                handler.setDrugName(update.drugName());
                changes = true;
            }
            if (update.isDrugStopped() != null && update.isDrugStopped() != handler.getIsDrugStopped()) {
                if (handler.getCrashUpdate() != null) {
                    changesMap.put("isDrugStopped" , handler.getIsDrugStopped().toString());
                }
                handler.setIsDrugStopped(update.isDrugStopped());
                changes = true;
            }
            if (changes) {
                handlerDao.updateDrugRequest(handler);
                changedItems.add(handler);
                handler.setCrashUpdate(changesMap);
            }
        }
        if (changedItems.isEmpty()) {
            throw new ResourceNotFoundException(
                    messages.getString("noChanges")
            );
        }

        dao.updateDrugRequestHandlerList(drugRequestHandlerList);
    }




}


