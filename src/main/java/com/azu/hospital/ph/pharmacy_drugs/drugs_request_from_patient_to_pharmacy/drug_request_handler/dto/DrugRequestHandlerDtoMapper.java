package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dto;

import com.azu.hospital.ph.main_data_store.drugs_item.entity.DrugsItem;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.entity.DrugRequestHandler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.function.Function;
@Service
public class DrugRequestHandlerDtoMapper implements Function<DrugRequestHandler, DrugRequestHandlerDto> {


    @Override
    public DrugRequestHandlerDto apply(DrugRequestHandler drugRequestHandler) {
        String itemName = null;

        if (drugRequestHandler.getDrugId() != null) {
            DrugsItem drugsItem = drugRequestHandler.getDrugsItems();
            if (drugsItem != null) {
                itemName = drugsItem.getItemName();
            } else {
                itemName = drugRequestHandler.getDrugName();
            }
        } else {
            itemName = drugRequestHandler.getDrugName();
        }

        return new DrugRequestHandlerDto(
                drugRequestHandler.getRequestId(),
                drugRequestHandler.getDrugId(),
                itemName,
                drugRequestHandler.getQuantity(),
                drugRequestHandler.getRequestStatus(),
                drugRequestHandler.getTimesDay(),
                drugRequestHandler.getTimesServing(),
                drugRequestHandler.getDoes(),
                drugRequestHandler.getRoa(),
                drugRequestHandler.getType(),
                drugRequestHandler.getIsDrugStopped(),
                drugRequestHandler.getIsDrugDeleted(),
                drugRequestHandler.getRejectCause(),
                drugRequestHandler.getNote(),
                drugRequestHandler.getCreateAt(),
                drugRequestHandler.getUpdateAt(),
                drugRequestHandler.getCrashUpdate()
//                drugRequestHandler.getCreatedBy(),
//                drugRequestHandler.getLastModifiedBy()
        );
    }
}
