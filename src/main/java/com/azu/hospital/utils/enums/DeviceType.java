package com.azu.hospital.utils.enums;

public enum DeviceType {
    FURNITURE("furniture"),
    DEVICE("device"),
    MEDICAL_DEVICE("medical device"),
    Bed("bed");

    private final String name;

    DeviceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
