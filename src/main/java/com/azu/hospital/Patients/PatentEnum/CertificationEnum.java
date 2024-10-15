package com.azu.hospital.Patients.PatentEnum;

public enum CertificationEnum {
    NO_CERTIFICATION("No Certification"),
    SCHOOL("School"),
    HIGH_SCHOOL("High School"),
    DIPLOMA("Diploma") ,
    BACHELOR("Bachelor") ,
    MASTER("Master") ,
    DOCTORATE("Doctorate");


    private String name ;

    CertificationEnum(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
