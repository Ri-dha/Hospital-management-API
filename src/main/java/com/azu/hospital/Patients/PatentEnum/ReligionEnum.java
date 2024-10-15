package com.azu.hospital.Patients.PatentEnum;

public enum ReligionEnum {

    MUSLIM("Muslim") ,
    CHRISTIAN("Christian") ,
    JEWISH("Jewish");


    private String name;
    ReligionEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
