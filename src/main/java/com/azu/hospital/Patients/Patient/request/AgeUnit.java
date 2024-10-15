package com.azu.hospital.Patients.Patient.request;

import java.util.Locale;

public enum AgeUnit {
    YEARS("years", "سنة"),
    MONTHS("months", "أشهر"),
    DAYS("days", "يوم"),
    HOURS("hours", "ساعة");

    private final String englishName;
    private final String arabicName;

    AgeUnit(String englishName, String arabicName) {
        this.englishName = englishName;
        this.arabicName = arabicName;
    }

    public String getName(Locale locale) {
        if (locale.getLanguage().equals("ar")) {
            return arabicName;
        } else {
            return englishName;
        }
    }
}