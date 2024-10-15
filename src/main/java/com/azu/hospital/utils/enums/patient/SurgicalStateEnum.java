package com.azu.hospital.utils.enums.patient;

public enum SurgicalStateEnum {
    InSurgical("Pre Surgical"),

    Waitting("waitting") ,
    Done("Finish Surgical");

    private String name;

    SurgicalStateEnum(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
