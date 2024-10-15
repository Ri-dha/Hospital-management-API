package com.azu.hospital.Patients.entryTable.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class EntryTableDateDto {
    private Long id;

    private Instant entryDate;

    public EntryTableDateDto(Long id,Instant entryDate) {
        this.id = id;
        this.entryDate = entryDate;
    }

}
