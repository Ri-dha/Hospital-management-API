package com.azu.hospital.utils.enums.notification;

public enum DestinationEnum {
    HOSPITAL("Hospital"),
    LAB("Lab"),
    ULTRASOUND("UltraSound"),
    ECG("Ecg"),
    RADIOLOGY("Radiology"),
    OPERATION("Operation");

    private final String name;
    DestinationEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}



