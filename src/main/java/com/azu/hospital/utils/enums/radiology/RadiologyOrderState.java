package com.azu.hospital.utils.enums.radiology;

public enum RadiologyOrderState {
    Rejected("Rejected"),

    Waitting("Waitting"),

    Accepted("Accepted"),

    Received("Received"),

    Completed("Completed");

    private String name;

    RadiologyOrderState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
