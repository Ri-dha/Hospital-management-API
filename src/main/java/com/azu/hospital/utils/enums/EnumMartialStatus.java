package com.azu.hospital.utils.enums;

public enum EnumMartialStatus {
    SINGLE("Single"),
    MARRIED("Married"),
    DIVORCED("Divorced"),
    WIDOWED("Widowed"),
    SEPARATED("Separated");

    private final String name;

    EnumMartialStatus(String status) {
        this.name = status;
    }

    public String getName() {
        return name;
    }

}
