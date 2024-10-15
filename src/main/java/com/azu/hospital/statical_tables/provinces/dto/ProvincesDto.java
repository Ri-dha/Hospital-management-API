package com.azu.hospital.statical_tables.provinces.dto;


import com.azu.hospital.statical_tables.district.dto.DistrictDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProvincesDto {
    private Long id;
    private String provinceName;
    private Long provinceCode;
    private List<DistrictDto> districts;
    public ProvincesDto() {
    }

    public ProvincesDto(Long id, String provinceName, Long provinceCode, List<DistrictDto> districts) {
        this.id = id;
        this.provinceName = provinceName;
        this.provinceCode = provinceCode;
        this.districts = districts;
    }
}
