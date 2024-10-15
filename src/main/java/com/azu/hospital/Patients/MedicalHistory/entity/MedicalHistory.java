package com.azu.hospital.Patients.MedicalHistory.entity;


import com.azu.hospital.Patients.Patient.Entity.Patient;

import com.azu.hospital.utils.enums.patient.PatientTriageEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "patient_medical_history")
@Getter
@Setter
public class MedicalHistory {

    @Id
    @SequenceGenerator(
            name = "medical_history_seq" ,
            sequenceName ="medical_history_seq"
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String dx;


    @Column(columnDefinition = "TEXT")
    private String contagious;
    @Column(columnDefinition = "TEXT")
    private String chiefComplaint ;

    @Column(columnDefinition = "TEXT")
    private String familyHistory;

    @Column(columnDefinition = "TEXT")
    private String drugHistoryAllergy;

    @Column(columnDefinition = "TEXT")
    private String historyPresentIllness;

    private PatientTriageEnum triage;
    private Instant daysOffWritten;

    private Instant updateAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient;


    public MedicalHistory(MedicalHistory entity) {
        this.dx = entity.getDx();
        this.contagious = entity.getContagious();
        this.chiefComplaint = entity.getChiefComplaint();
        this.familyHistory = entity.getFamilyHistory();
        this.drugHistoryAllergy = entity.getDrugHistoryAllergy();
        this.historyPresentIllness = entity.getHistoryPresentIllness();
        this.triage = entity.getTriage();
    }

    @PrePersist
    @Column(updatable = false)
    public void setDaysOffWritten() {
        this.daysOffWritten = Instant.now();
    }

    public MedicalHistory() {
    }

    public MedicalHistory(
            String dx,
            String contagious,
            String chiefComplaint,
            String familyHistory,
            String drugHistoryAllergy,
            String historyPresentIllness,
            PatientTriageEnum triage

    ) {
        this.dx = dx;
        this.contagious = contagious;
        this.chiefComplaint = chiefComplaint;
        this.familyHistory = familyHistory;
        this.drugHistoryAllergy = drugHistoryAllergy;
        this.historyPresentIllness = historyPresentIllness;
        this.triage = triage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDx(String dx) {
        this.dx = dx;
    }

    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory;
    }

    public void setDrugHistoryAllergy(String drugHistoryAllergy) {
        this.drugHistoryAllergy = drugHistoryAllergy;
    }

    public void setHistoryPresentIllness(String historyPresentIllness) {
        this.historyPresentIllness = historyPresentIllness;
    }

    public void setDaysOffWritten(Instant daysOffWritten) {
        this.daysOffWritten = daysOffWritten;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


    public void setTriage(PatientTriageEnum triage) {
        this.triage = triage;
    }

    @PreUpdate
    public void updateAt() {
        this.updateAt = Instant.now();
    }


    public void setContagious(String contagious) {
        this.contagious = contagious;
    }
}
