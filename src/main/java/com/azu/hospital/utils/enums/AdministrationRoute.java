package com.azu.hospital.utils.enums;
import java.util.Locale;

public enum AdministrationRoute {
    ORAL_ROUTE("Oral Route", "المسار الفموي"),
    SUBLINGUAL_ROUTE("Sublingual Route", "المسار تحت اللسان"),
    BUCCAL_ROUTE("Buccal Route", "المسار الخدي"),
    INTRAVENOUS_ROUTE("Intravenous Route", "المسار الوريدي"),
    INTRAMUSCULAR_ROUTE("Intramuscular Route", "المسار العضلي"),
    SUBCUTANEOUS_ROUTE("Subcutaneous Route", "المسار تحت الجلد"),
    INHALATION_ROUTE("Inhalation Route", "المسار التنفسي"),
    NASAL_ROUTE("Nasal Route", "المسار الأنفي"),
    RECTAL_ROUTE("Rectal Route", "المسار المستقيم"),
    VAGINAL_ROUTE("Vaginal Route", "المسار المهبلي"),
    CUTANEOUS_ROUTE("Cutaneous Route", "المسار الجلدي"),
    OTIC_ROUTE("Otic Route", "المسار الأذني"),
    OCULAR_ROUTE("Ocular Route", "المسار العيني"),
    TRANSDERMAL_ROUTE("Transdermal Route", "المسار الجلدي العابر");

    private final String englishName;
    private final String arabicName;

    AdministrationRoute(String englishName, String arabicName) {
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
