package com.azu.hospital.utils.enums.storeHose;


public enum StoreHoseItemEnum {

    EDTA_Tube("EDTA Tube") ,
    Serum_Tube("Serum Tube") ,
    Coagulation_Tube("Coagulation Tube"),
    Heparin_Tube("Heparin Tube"),
    Sodium_Fluoride("Sodium_Fluoride") ,
    ESR_Tube("ESR Tube"),
    METAL_FREE("METAL Free"),

    PLAIN_BLOOD("Plain blood"),
    Urine_Cup("Urine Cup"),
    Swab_Stick("Swab Stick");

    private String name;

    StoreHoseItemEnum(String name) {
        this.name = name ;
    }

    public String getName(){
        return this.name;
    }
}