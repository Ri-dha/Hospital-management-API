package com.azu.hospital.lab_collection.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeCardDto {

    private Long totalPatient;
    private Integer mostCommonAnalysis;
    private Long totalAnalysis;

    public HomeCardDto(Long totalPatient, Integer mostCommonAnalysis, Long totalAnalysis) {
        this.totalPatient = totalPatient;
        this.mostCommonAnalysis = mostCommonAnalysis;
        this.totalAnalysis = totalAnalysis;
    }

    public HomeCardDto() {
    }
}
