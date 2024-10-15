package com.azu.hospital.Patients.PatentEnum;

public enum JobTypeEnum {

    GENERAL("General"),
    PRIVATE("Private"),
    FOREIGN_COMP("Private Company");


    private String name;
    JobTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
