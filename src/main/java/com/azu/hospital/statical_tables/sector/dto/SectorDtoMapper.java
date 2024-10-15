package com.azu.hospital.statical_tables.sector.dto;


import com.azu.hospital.statical_tables.sector.entity.Sector;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SectorDtoMapper implements Function<Sector, SectorDto> {

    @Override
    public SectorDto apply(Sector sector) {
        return new SectorDto(
                sector.getId(),
                sector.getSectorName(),
                sector.getSectorCode()
        );
    }
}
