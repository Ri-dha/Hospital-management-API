package com.azu.hospital.utils.enums;

public enum AnesthesiaType {
    GENERAL,
    REGIONAL,
    LOCAL,
    SEDATION;

    public String getName() {
        return this.name();
    }
}
