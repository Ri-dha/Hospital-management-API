package com.azu.hospital.utils.enums;

public enum EnumItemType {

    DRUGS("Drugs"),
    CONSUMABLES("Consumables"),
    LABORATORY("Laboratory"),
    RADIOLOGY("Radiology"),
    OPERATIONS("Operations"),
    OTHER("Other");


    private final String name;

    EnumItemType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
