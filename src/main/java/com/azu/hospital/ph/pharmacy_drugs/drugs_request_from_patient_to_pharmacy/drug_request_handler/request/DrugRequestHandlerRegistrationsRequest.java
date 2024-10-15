package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.request;

import com.azu.hospital.ph.mediciens.type.MedicineType;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import jakarta.annotation.Nullable;

import java.time.Instant;

public record   DrugRequestHandlerRegistrationsRequest(
        Long signaturesId,
        @Nullable
        Long drugId,
        String drugName,
        Integer quantity,
        Long patientId,
        String timesDay,
        String timesServing,
        String does,
        String roa,
        MedicineType type,
        String drugNote,
        String note

) {

}