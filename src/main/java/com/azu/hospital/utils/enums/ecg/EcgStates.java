package com.azu.hospital.utils.enums.ecg;

public enum EcgStates {

    Waitting,
    WatingAcceptDoctor,
    WaitingFromDoctor,
    Accepted,
    CancelFromDoctor,
    Completed;


    private String name;

    EcgStates() {
    }

    EcgStates(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
