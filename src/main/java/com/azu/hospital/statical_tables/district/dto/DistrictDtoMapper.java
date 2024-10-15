package com.azu.hospital.statical_tables.district.dto;


import com.azu.hospital.statical_tables.district.entity.District;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DistrictDtoMapper implements Function<District, DistrictDto> {


    @Override
    public DistrictDto apply(District district) {
        return new DistrictDto(
                district.getId(),
                district.getDistrictName(),
                district.getDistrictCode()
        );
    }
}
