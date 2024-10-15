package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dto;

import com.azu.hospital.ph.mediciens.type.MedicineType;
import com.azu.hospital.utils.enums.Gender;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;

import java.time.Instant;
import java.util.Map;

public record DrugRequestHandlerDto(
        Long requestId,
        Long drugId,
        String drugTradeName,
        Integer quantity,
        UnitInventoryRequestEnum requestStatus,
        String timesDay,
        String timesServing,
        String does,
        String roa,
        MedicineType type,
        Boolean isDrugStopped,
        Boolean isDrugDeleted,
        String rejectCause,
        String note,
        Instant createAt,
        Instant updateAt,
        Map<String, String> crashUpdate
//        Long createdBy,
//        Long LastModifiedBy

        ) {


}
