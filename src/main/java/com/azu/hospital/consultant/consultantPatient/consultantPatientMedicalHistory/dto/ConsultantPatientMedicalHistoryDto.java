package com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.dto;


import jakarta.persistence.Column;
import lombok.Data;

import java.time.Instant;

@Data
public class ConsultantPatientMedicalHistoryDto {


    private Long id;

    private Long patientId;

    private String dx;

    private String historyOfPresentIllness;

    private String pastMedicalHistory;

    private String pastSurgicalHistory;

    private Instant createdAt;

    private Instant updatedAt;


    public ConsultantPatientMedicalHistoryDto(
            Long id,
            Long patientId,
            String dx,
            String historyOfPresentIllness,
            String pastMedicalHistory,
            String pastSurgicalHistory,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.patientId = patientId;
        this.dx = dx;
        this.historyOfPresentIllness = historyOfPresentIllness;
        this.pastMedicalHistory = pastMedicalHistory;
        this.pastSurgicalHistory = pastSurgicalHistory;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ConsultantPatientMedicalHistoryDto() {
    }
}
