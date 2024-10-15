package com.azu.hospital.newPh.Billing.requests;

import com.azu.hospital.newPh.Billing.BillingType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicinesBillingCreateRequest {
    private Long medicinesRequestsListId;
    private Long patientId;
    private String finalPrice;
    private BillingType billingType;


    public MedicinesBillingCreateRequest(Long medicinesRequestsListId, Long patientId, String finalPrice, BillingType billingType) {
        this.medicinesRequestsListId = medicinesRequestsListId;
        this.patientId = patientId;
        this.finalPrice = finalPrice;
        this.billingType = billingType;
    }

    public MedicinesBillingCreateRequest() {
    }

    public MedicinesBillingCreateRequest(String finalPrice, BillingType billingType) {
        this.finalPrice = finalPrice;
        this.billingType = billingType;
    }
}
