package com.azu.hospital.ecg.external.dto;

import com.azu.hospital.ecg.external.entity.EcgPatientData;
import com.azu.hospital.utils.enums.ecg.EcgStates;
import com.azu.hospital.utils.files.File;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class ExternalEcgDto {

    private Long id;

    private EcgPatientData patientData;

    private Long nurseId;

    private String nurseName;

    private EcgStates state;

    private List<File> files;

    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    public ExternalEcgDto(
            Long id,
            EcgPatientData patientData,
            Long nurseId,
            String nurseName,
            EcgStates state,
            List<File> files,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.nurseId = nurseId;
        this.patientData = patientData;
        this.nurseName = nurseName;
        this.state = state;
        this.files = files;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ExternalEcgDto() {
    }
}
