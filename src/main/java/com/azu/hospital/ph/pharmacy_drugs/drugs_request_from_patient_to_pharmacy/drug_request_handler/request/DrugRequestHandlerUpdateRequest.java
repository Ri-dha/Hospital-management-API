package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.request;

import com.azu.hospital.ph.mediciens.type.MedicineType;

public record DrugRequestHandlerUpdateRequest (
        Long requestId,
    Integer quantity,
    String drugName,
    String timesDay,
    String timesServing,
    String does,
    String roa,
    MedicineType type,
    String note,
        Boolean isDrugStopped
){
}
