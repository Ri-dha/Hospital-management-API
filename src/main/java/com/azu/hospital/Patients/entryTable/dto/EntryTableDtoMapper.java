package com.azu.hospital.Patients.entryTable.dto;


import com.azu.hospital.Patients.entryTable.entity.EntryTable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EntryTableDtoMapper implements Function<EntryTable, EntryTableDto> {
    @Override
    public EntryTableDto apply(EntryTable entryTable) {
        return new EntryTableDto(
                entryTable.getId(),
                entryTable.getTimesOfEntry(),
                entryTable.getEntryDate()
        );
    }
}
