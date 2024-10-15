package com.azu.hospital.Patients.charts.nursingObservation.dto;

import com.azu.hospital.ph.mediciens.type.MedicineType;
import com.azu.hospital.utils.enums.AdministrationRoute;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObservationTimeDto {

    private Long requestId;
    private String time;
    private String timeStatus;
    private MedicineType type;
    private String does;
    private String roa;
    private String drugName;

    public ObservationTimeDto(Long requestId, String time, String timeStatus, MedicineType type, String does, String roa, String drugName) {

        this.requestId = requestId;
        this.time = time;
        this.timeStatus = timeStatus;
        this.type = type;
        this.does = does;
        this.roa = roa;
        this.drugName = drugName;
    }
}
