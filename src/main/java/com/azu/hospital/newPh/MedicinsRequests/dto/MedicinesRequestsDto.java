package com.azu.hospital.newPh.MedicinsRequests.dto;


import com.azu.hospital.ph.mediciens.type.MedicineType;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicinesRequestsDto {

    private Long id;
    private MedicineType type;
    private UnitInventoryRequestEnum requestStatus;
    private String timesDay;
    private String timesServing;
    private String does;
    private Long quantity;
    private String roa;
    private String note;
    private Long medicinesId;
    private String medicinesName;

    public MedicinesRequestsDto() {
    }


    public MedicinesRequestsDto(Long id, MedicineType type, UnitInventoryRequestEnum requestStatus, String timesDay, String timesServing, String does, Long quantity, String roa, String note, Long medicinesId, String medicinesName) {
        this.id = id;
        this.type = type;
        this.requestStatus = requestStatus;
        this.timesDay = timesDay;
        this.timesServing = timesServing;
        this.does = does;
        this.quantity = quantity;
        this.roa = roa;
        this.note = note;
        this.medicinesId = medicinesId;
        this.medicinesName = medicinesName;
    }







}
