package com.azu.hospital.patient_expances.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class PatientExpanseDto {

    private Long Id;
    private Long consumableId;
    private String barcode;
    private String itemName;
    private Integer itemCount;
    private String note;
    private Instant createdAt;
    private Instant updatedAt;

    public PatientExpanseDto() {
    }

    public PatientExpanseDto(Long id, Long consumableId, String barcode, String itemName, Integer count, String note, Instant createdAt, Instant updatedAt) {
        Id = id;
        this.consumableId = consumableId;
        this.barcode = barcode;
        this.itemName = itemName;
        this.itemCount = count;
        this.note = note;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
