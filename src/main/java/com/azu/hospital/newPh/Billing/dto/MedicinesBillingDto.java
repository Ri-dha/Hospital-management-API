package com.azu.hospital.newPh.Billing.dto;

import com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList.entity.MedicinesRequestsList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicinesBillingDto {

    private Long id;
    private String billingType;
    private String finalPrice;
    private Long quantity;
    private Long totalItems;
    private MedicinesRequestsList medicinesRequestsList;
    private Long patientId;
    private String patientName;
    private String createAt;
    private String updateAt;

    public MedicinesBillingDto() {
    }

    public MedicinesBillingDto(Long id, String billingType, String finalPrice, Long quantity, Long totalItems, MedicinesRequestsList medicinesRequestsList, Long patientId, String patientName, String createAt, String updateAt) {
        this.id = id;
        this.billingType = billingType;
        this.finalPrice = finalPrice;
        this.quantity = quantity;
        this.totalItems = totalItems;
        this.medicinesRequestsList = medicinesRequestsList;
        this.patientId = patientId;
        this.patientName = patientName;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }


}
