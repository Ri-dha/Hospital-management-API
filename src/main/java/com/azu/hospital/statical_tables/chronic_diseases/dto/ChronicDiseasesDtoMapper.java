package com.azu.hospital.statical_tables.chronic_diseases.dto;

import com.azu.hospital.statical_tables.chronic_diseases.entity.ChronicDiseases;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ChronicDiseasesDtoMapper implements Function<ChronicDiseases, ChronicDiseasesDto> {
    @Override
    public ChronicDiseasesDto apply(ChronicDiseases chronicDiseases) {
        return new ChronicDiseasesDto(
                chronicDiseases.getId(),
                chronicDiseases.getDiseaseName(),
                chronicDiseases.getDiseaseCode()
        );
    }
}
