package com.azu.hospital.statical_tables.diseases.dto;


import com.azu.hospital.statical_tables.diseases.entity.Diseases;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DiseasesDtoMapper implements Function<Diseases, DiseasesDto>{
    @Override
    public DiseasesDto apply(Diseases diseases) {
        return new DiseasesDto(
                diseases.getId(),
                diseases.getCode(),
                diseases.getName(),
                diseases.getSerialNumber()
        );
    }
}
