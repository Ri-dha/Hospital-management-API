package com.azu.hospital.Patients.entryTable.dto;


import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class EntryTableDto {

    private Long id;

    private Long timesOfEntry;

    private Instant entryDate;





    public EntryTableDto(Long id, Long timesOfEntry, Instant entryDate) {
        this.id = id;
        this.timesOfEntry = timesOfEntry;
        this.entryDate = entryDate;
    }
}
