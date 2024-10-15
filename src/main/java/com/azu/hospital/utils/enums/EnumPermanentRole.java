package com.azu.hospital.utils.enums;

public enum EnumPermanentRole {
    PERMANENT("Permanent"),
    PERMANENT_DOCTORS_HEAD_CHIEF("Permanent Doctors Head Chief"),
    PERMANENT_DOCTORS_HEAD_CHIEF_ASSISTANT("Permanent Doctors Head Chief Assistant");

    private final String roleName;

    EnumPermanentRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
