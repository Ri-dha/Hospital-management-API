package com.azu.hospital.utils.enums.building;

public enum WorkState {

    OFF_WORK("Off Work") ,
    ON_DUTY("On Duty"),
    ABSENT("Absent");

    private final String name;
    WorkState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
