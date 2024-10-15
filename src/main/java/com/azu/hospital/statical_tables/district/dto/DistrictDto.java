package com.azu.hospital.statical_tables.district.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistrictDto {
    private Long id;

    private String districtName;

    private Long districtCode;

    public DistrictDto() {
    }

    public DistrictDto(Long id, String districtName, Long districtCode) {
        this.id = id;
        this.districtName = districtName;
        this.districtCode = districtCode;
    }


}
