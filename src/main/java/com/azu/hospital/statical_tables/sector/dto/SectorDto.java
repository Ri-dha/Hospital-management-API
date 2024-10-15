package com.azu.hospital.statical_tables.sector.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectorDto {

    private Long id;

    private String sectorName;

    private Long sectorCode;


    public SectorDto() {
    }

    public SectorDto(Long id, String sectorName, Long sectorCode) {
        this.id = id;
        this.sectorName = sectorName;
        this.sectorCode = sectorCode;
    }
}
