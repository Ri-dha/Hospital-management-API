package com.azu.hospital.utils.enums.ultrasound;

public enum UltrasoundOrderState {
    WaittingForDoctorAccept("Waitting for doctor accept"),
    DoctorCancel("Canceled"),

    Waitting("Waitting"),

    Accepted("Accepted"),

    Rejected("Rejected"),

    Received("Received"),
    Completed("Completed");

    private String name;

    UltrasoundOrderState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
