package com.azu.hospital.utils.enums.lab;

public enum InternalLabRequestStatusEnum {
    WaittingAcceptFromDoctor("Waiting Doctor Accept") ,
    DoctorCancel("Cancel By Doctor") ,

    Waitting("Waitting"),
    GettingSample("Getting Sample") ,
    SampleReceived("Sample Received") ,

    Archived("Archived"),

    Complete("Complete");


    private String name;

    InternalLabRequestStatusEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
