package com.azu.hospital.utils.enums.radiology;

public enum RadiologyTypeEnum {

    XRay("X-Ray"),
    XRayWithDye("X-Ray With Dye"),
    MRI("MRI"),
    MRIWithDye("MRI With Dye"),
    CT_Scan("CT-Scan"),
    CT_ScanWithDye("CT-Scan With Dye")
    ;


    private String name;

    RadiologyTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
