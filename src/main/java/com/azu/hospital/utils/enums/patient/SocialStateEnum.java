package com.azu.hospital.utils.enums.patient;

public enum SocialStateEnum {

    SINGLE("Single"),
    MARRIED("Married"),
    DIVORCED("Divorced"),

    WIDOW_WIDOWER("Widow/Widower");

    private String name;

    SocialStateEnum(String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
