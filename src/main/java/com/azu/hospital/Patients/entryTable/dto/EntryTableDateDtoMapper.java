package com.azu.hospital.Patients.entryTable.dto;


import com.azu.hospital.Patients.entryTable.entity.EntryTable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EntryTableDateDtoMapper implements Function<EntryTable, EntryTableDateDto> {
    @Override
    public EntryTableDateDto apply(EntryTable entryTable) {
        return new EntryTableDateDto(
                entryTable.getId(),
                entryTable.getEntryDate()
        );
    }
}
