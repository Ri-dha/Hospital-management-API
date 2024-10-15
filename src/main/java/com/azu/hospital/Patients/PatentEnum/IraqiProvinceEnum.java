package com.azu.hospital.Patients.PatentEnum;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

public enum IraqiProvinceEnum {
    BAGHDAD("Baghdad", "بغداد"),
    NINEVEH("Nineveh", "نينوى"),
    BASRA("Basra", "البصرة"),
    SALAHUDDIN("Salahuddin", "صلاح الدين"),
    DUHOK("Dohuk", "دهوك"),
    ERBIL("Erbil", "أربيل"),
    SULAYMANIYAH("Sulaymaniyah", "السليمانية"),
    DIYALA("Diyala", "ديالى"),
    WASIT("Wasit", "واسط"),
    MAYSAN("Maysan", "ميسان"),
    DHI_QAR("Dhi Qar", "ذي قار"),
    MUTHANNA("Muthanna", "المثنى"),
    BABYLON("Babylon", "بابل"),
    KARBALA("Karbala", "كربلاء"),
    NAJAF("Najaf", "النجف"),
    ANBAR("Anbar", "الأنبار"),
    DIWANIYAH("Diwaniyah", "الديوانية"),
    KIRKIK("Kirkuk", "كركوك"),
    HALABJA("Halabja", "حلبجة");

    private String englishName;
    private String arabicName;

    IraqiProvinceEnum(String englishName, String arabicName) {
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




