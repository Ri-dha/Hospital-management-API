package com.azu.hospital.utils.Generic;

import com.azu.hospital.Patients.PatentEnum.IraqiProvinceArabicEnum;

public class IraqiProvinceDto {
    private final String name;
    private final String translation;

    public IraqiProvinceDto(IraqiProvinceArabicEnum province, String translation) {
        this.name = province.name();
        this.translation = translation;
    }

    public String getName() {
        return name;
    }

    public String getTranslation() {
        return translation;
    }
}
