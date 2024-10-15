package com.azu.hospital.utils.enums.patient;

public enum JopTypeEnum {
    GENERAL("General"),
    PRIVATE("Private"),
    FOREIGN_COMPANY("Foreign Company");

    private String name;

    JopTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
