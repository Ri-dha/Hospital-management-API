package com.azu.hospital.utils.enums.lab;

public enum LabRequestStatusEnum {
    GettingSample("Getting Sample") ,
    SampleReceived("Sample Received") ,

    Cancel("Cancel"),
    Complete("Complete"),

    Archived("Archived");


    private String name;

    LabRequestStatusEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
