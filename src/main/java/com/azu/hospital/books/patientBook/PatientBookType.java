package com.azu.hospital.books.patientBook;

public enum PatientBookType {

    PLEDGE_OF_CONSENT_FOR_SURGERY("Pledge of consent for surgery"),
    PLEDGE_OF_CONSENT_FOR_PREMATURE_BABY("Pledge of consent for premature baby"),
    CONSENT_FOR_DISCHARGE_AT_OWN_RISK("Consent for discharge at own risk");

    private String name;

    PatientBookType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
