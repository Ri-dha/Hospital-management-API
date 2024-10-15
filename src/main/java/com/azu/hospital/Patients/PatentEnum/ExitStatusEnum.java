package com.azu.hospital.Patients.PatentEnum;

public enum ExitStatusEnum {
    RECOVERED("Recovered"),
    DEAD("Dead") ,
    ON_HIS_HER_RESPONSIBILITY("On His/Her Responsibility");

    private String name;

    ExitStatusEnum(String name) {
        this.name = name ;
    }

    public String getName() {
        return name;
    }
}
