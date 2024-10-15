package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.request;

import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.request.DrugRequestHandlerUpdateRequest;

import java.util.List;

public record DrugRequestHandlerListUpdateRequest (
        List<DrugRequestHandlerUpdateRequest> request
){
}
