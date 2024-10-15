package com.azu.hospital.statical_tables.diseases.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiseasesDto {


    private Long id;

    private String code;

    private String name;

    private String serialNumber;

    public DiseasesDto() {
    }

    public DiseasesDto(String code, String name, String serialNumber) {
        this.code = code;
        this.name = name;
        this.serialNumber = serialNumber;
    }

    public DiseasesDto(Long id, String code, String name, String serialNumber) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.serialNumber = serialNumber;
    }


}
