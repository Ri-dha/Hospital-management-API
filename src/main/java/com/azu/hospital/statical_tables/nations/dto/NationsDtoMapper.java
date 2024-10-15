package com.azu.hospital.statical_tables.nations.dto;


import com.azu.hospital.statical_tables.nations.entity.Nations;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class NationsDtoMapper implements Function<Nations, NationsDto> {
    @Override
    public NationsDto apply(Nations nations) {
        return new NationsDto(
                nations.getNations_id(),
                nations.getNationsTitle(),
                nations.getNationsCode()
        );
    }
}
