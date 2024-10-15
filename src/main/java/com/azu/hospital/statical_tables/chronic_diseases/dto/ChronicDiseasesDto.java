package com.azu.hospital.statical_tables.chronic_diseases.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChronicDiseasesDto {
    private Long id;

    private String diseaseName;
    private Long diseaseCode;




    public ChronicDiseasesDto(Long id, String diseaseName, Long diseaseCode) {
        this.id = id;
        this.diseaseName = diseaseName;
        this.diseaseCode = diseaseCode;
    }
}
