package com.azu.hospital.utils.enums;

public enum LabTubeStorageType {
    Refrigerators_Freezers("Refrigerators and freezers"),
    Flammable_Storage_Cabinets("Flammable storage cabinets"),
    Chemical_Storage_Cabinets("Chemical storage cabinets"),
    Glassware_Storage_Cabinets("Glassware storage cabinets"),
    Centrifuge_Microscope_Storage_Cabinets("Centrifuge and microscope storage cabinets"),
    Other_Storage_Way("Other Storage Way"),
    ;


    private final String name;



    LabTubeStorageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
