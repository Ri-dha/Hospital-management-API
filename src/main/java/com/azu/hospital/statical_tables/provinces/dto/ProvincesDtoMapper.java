package com.azu.hospital.statical_tables.provinces.dto;


import com.azu.hospital.statical_tables.district.dto.DistrictDtoMapper;
import com.azu.hospital.statical_tables.provinces.entity.Provinces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProvincesDtoMapper implements Function<Provinces, ProvincesDto> {
    private final DistrictDtoMapper districtDtoMapper;

    @Autowired
    public ProvincesDtoMapper(@Qualifier("districtDtoMapper") DistrictDtoMapper districtDtoMapper) {
        this.districtDtoMapper = districtDtoMapper;
    }

    @Override
    public ProvincesDto apply(Provinces provinces) {
        return new ProvincesDto(
                provinces.getId(),
                provinces.getProvinceName(),
                provinces.getProvinceCode(),
                provinces.getDistrict().stream()
                        .map(districtDtoMapper)
                        .toList()
        );
    }
}
