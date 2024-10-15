package com.azu.hospital.utils.enums.bottles;

public enum BloodGroupEnum {
    A_POS("A+"),
    A_NEG("A-") ,
    B_POS("B+"),
    B_NEG("B-") ,
    AB_POS("AB+"),
    AB_NEG("AB-") ,
    O_POS("O+"),
    O_NEG("O-");




    private String name;

    BloodGroupEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}