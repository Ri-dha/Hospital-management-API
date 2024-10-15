package com.azu.hospital.newPh.Billing;

import lombok.Getter;

@Getter
public enum BillingType {
    IN_HOSPITAL("In Hospital"),
    OUT_HOSPITAL("Out Hospital")
    ;
    private final String name;

    BillingType(String name) {
        this.name = name;
    }

}
