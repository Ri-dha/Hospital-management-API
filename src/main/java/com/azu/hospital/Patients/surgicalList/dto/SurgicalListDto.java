package com.azu.hospital.Patients.surgicalList.dto;

import com.azu.hospital.utils.enums.patient.AnesthesiaEnum;
import com.azu.hospital.utils.enums.patient.SurgicalStateEnum;
import lombok.Data;

import java.time.Instant;

@Data
public class SurgicalListDto {

    private Long id;

    private String surgicalName;

    private AnesthesiaEnum anesthesiaType;

    private SurgicalStateEnum state;

    private Long patientId ;

    private String patientName;

    private String patientAge;

    private Instant surgicalDate;

    private Instant createdAt;

    private Instant updatedAt;

    public SurgicalListDto(
            Long id,
            String surgicalName,
            AnesthesiaEnum anesthesiaType,
            SurgicalStateEnum state,
            Long patientId,
            String patientName,
            String patientAge,
            Instant surgicalDate,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.surgicalName = surgicalName;
        this.anesthesiaType = anesthesiaType;
        this.state = state;
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.surgicalDate = surgicalDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public SurgicalListDto() {
    }
}
