package com.azu.hospital.utils.enums.appointment;

public enum AppointmentsState {

    Waitting("Waitting") ,
    With_The_Doctor("With The Doctor") ,
    Done("Done"),
    CANCEL("Done")
    ;



    private String name;

    public String getName(){
        return this.name;
    }

    AppointmentsState(String name) {
        this.name = name;
    }

}