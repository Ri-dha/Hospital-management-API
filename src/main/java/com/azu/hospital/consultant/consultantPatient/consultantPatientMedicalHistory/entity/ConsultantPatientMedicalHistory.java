package com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.entity;


import com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.dto.ConsultantPatientMedicalHistoryEntityDto;
import com.azu.hospital.consultant.consultantPatient.entity.ConsultantPatient;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;


@Entity
@Table(name = "consultant_patient_medical_history")
@Data
public class ConsultantPatientMedicalHistory {

    @Id
    @SequenceGenerator(
            name = "consultant_patient_medical_history_id_seq",
            sequenceName = "consultant_patient_medical_history_id_seq"
    )

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String dx;

    @Column(columnDefinition = "TEXT")
    private String historyOfPresentIllness;

    @Column(columnDefinition = "TEXT")
    private String pastMedicalHistory;

    @Column(columnDefinition = "TEXT")
    private String pastSurgicalHistory;


    @Column(columnDefinition = "TEXT")
    private String allergy;

    @Column(columnDefinition = "TEXT")
    private String contagiousDisease;


    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private ConsultantPatient consultantPatient;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }

    public ConsultantPatientMedicalHistory(
            String dx,
            String historyOfPresentIllness,
            String pastMedicalHistory,
            String pastSurgicalHistory,
            String allergy ,
            String contagiousDisease
    ) {
        this.dx = dx;
        this.historyOfPresentIllness = historyOfPresentIllness;
        this.pastMedicalHistory = pastMedicalHistory;
        this.pastSurgicalHistory = pastSurgicalHistory;
        this.allergy = allergy;
        this.contagiousDisease = contagiousDisease;
    }

    public ConsultantPatientMedicalHistory(ConsultantPatientMedicalHistoryEntityDto medicalHistory) {
        this.dx = medicalHistory.getDx();
        this.pastMedicalHistory = medicalHistory.getPastMedicalHistory();
        this.historyOfPresentIllness = medicalHistory.getHistoryOfPresentIllness();
        this.pastSurgicalHistory = medicalHistory.getPastSurgicalHistory();
        this.allergy = medicalHistory.getAllergy();
        this.contagiousDisease =medicalHistory.getContagiousDisease();
    }

    public ConsultantPatientMedicalHistory() {
    }
}
