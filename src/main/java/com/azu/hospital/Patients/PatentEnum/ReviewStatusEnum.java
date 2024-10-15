package com.azu.hospital.Patients.PatentEnum;

public enum ReviewStatusEnum {

    URGENT("Urgent"),
    SCHEDULED("Scheduled");


    private String name ;

    ReviewStatusEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
