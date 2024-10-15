package com.azu.hospital.utils.enums.Shift;

public enum ShiftType {

    MORNING("morning"),
    EVENING("evening"),
    NIGHT("night");

    private final String value;

    ShiftType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
