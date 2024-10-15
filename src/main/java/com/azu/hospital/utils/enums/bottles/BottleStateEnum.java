package com.azu.hospital.utils.enums.bottles;

public enum BottleStateEnum {

    NoArchive("NoArchive"),
    WaittingDoctorAccept("WaittingDoctorAccept"),
    Waiting("Waiting") ,

    Preparing("Preparing"),
    Ready("Ready"),

    Pending("Pending"),

    Received("Received"),
    NotReceived("NotReceived"),

    Archived("Archived");

    private String name;

    BottleStateEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
