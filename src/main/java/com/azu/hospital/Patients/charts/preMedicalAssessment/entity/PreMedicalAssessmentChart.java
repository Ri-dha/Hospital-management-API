package com.azu.hospital.Patients.charts.preMedicalAssessment.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.physical_assessment_enm.CardiacCondition;
import com.azu.hospital.Patients.charts.physical_assessment_enm.MedicalCategory;
import com.azu.hospital.Patients.charts.physical_assessment_enm.MedicalConditionEnum;
import com.azu.hospital.Patients.charts.physical_assment.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Map;


@Entity
@Table(name = "pre_medical_assessment_chart")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class PreMedicalAssessmentChart {

    @Id
    @SequenceGenerator(
            name = "pre_medical_assessment_chart_sequence",
            sequenceName = "pre_medical_assessment_chart_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String chartName = "Pre Medical Assessment Chart";

    private String plannedSurgicalProcedure;

    @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "Date Should be Like YYYY-MM-DD"
    )

    private String surgeryDate;

    private String surgeryLocation;

    private String attendingSurgeon;

    private String presentIllnessHistory;

    // TODO: 9/13/2023 Allergies/Reaction will implement by relation

    @Column(columnDefinition = "TEXT")
    private String pastMedicalHistoryNote;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<MedicalConditionEnum, Boolean> pastMedicalHistoryTable;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<CardiacCondition, Boolean> cardiacHistory;

    @Embedded
    private SocialHistory socialHistory;

    @Embedded
    private PriorAnesthesiaComplication priorAnesthesiaComplication;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<MedicalCategory, Boolean> symptomsSystem;

    private Double bmi;

    @Embedded
    private NormalExamCheck normalExamCheck;

    @Embedded
    private SurgicalRisk surgicalRisk;

    @Embedded
    private CardiacRiskAssessment cardiacRiskAssessment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;


    @CreatedBy
    @Column(
            updatable = false
    )
    private Long createdBy;
    @LastModifiedBy
    @Column(
            insertable = false
    )
    private Long LastModifiedBy;

    public PreMedicalAssessmentChart() {
    }

    public PreMedicalAssessmentChart(Builder builder) {
        this.plannedSurgicalProcedure = builder.chart.plannedSurgicalProcedure;
        this.surgeryDate = builder.chart.surgeryDate;
        this.surgeryLocation = builder.chart.surgeryLocation;
        this.attendingSurgeon = builder.chart.attendingSurgeon;
        this.presentIllnessHistory = builder.chart.presentIllnessHistory;
        this.pastMedicalHistoryNote = builder.chart.pastMedicalHistoryNote;
        this.pastMedicalHistoryTable = builder.chart.pastMedicalHistoryTable;
        this.priorAnesthesiaComplication = builder.chart.priorAnesthesiaComplication;
        this.socialHistory = builder.chart.socialHistory;
        this.cardiacHistory = builder.chart.cardiacHistory;
        this.symptomsSystem = builder.chart.symptomsSystem;
        this.bmi = builder.chart.bmi;
        this.normalExamCheck = builder.chart.normalExamCheck;
        this.surgicalRisk = builder.chart.surgicalRisk;
        this.cardiacRiskAssessment = builder.chart.cardiacRiskAssessment;
    }



    public static class Builder {
        private final PreMedicalAssessmentChart chart = new PreMedicalAssessmentChart();

        public Builder withPlannedSurgicalProcedure(String plannedSurgicalProcedure) {
            chart.plannedSurgicalProcedure = plannedSurgicalProcedure;
            return this;
        }

        public Builder withSurgeryDate(String surgeryDate) {
            chart.surgeryDate = surgeryDate;
            return this;
        }

        public Builder withSurgeryLocation(String attendingSurgeon) {
            chart.surgeryLocation = attendingSurgeon;
            return this;
        }

        public Builder withAttendingSurgeon(String attendingSurgeon) {
            chart.attendingSurgeon = attendingSurgeon;
            return this;
        }

        public Builder withPresentIllnessHistory(String presentIllnessHistory) {
            chart.presentIllnessHistory = presentIllnessHistory;
            return this;
        }

        public Builder withPastMedicalHistoryNote(String withPastMedicalHistoryNote) {
            chart.pastMedicalHistoryNote = withPastMedicalHistoryNote;
            return this;
        }

        public Builder withPastMedicalHistoryTable(Map<MedicalConditionEnum, Boolean> pastMedicalHistoryTable) {
            chart.pastMedicalHistoryTable = pastMedicalHistoryTable;
            return this;
        }

        public Builder withPriorAnesthesiaComplication(PriorAnesthesiaComplication priorAnesthesiaComplication) {
            chart.priorAnesthesiaComplication = priorAnesthesiaComplication;
            return this;
        }

        public Builder withSocialHistory(SocialHistory socialHistory) {
            chart.socialHistory = socialHistory;
            return this;
        }

        public Builder withCardiacHistory(Map<CardiacCondition, Boolean> cardiacHistory) {
            chart.cardiacHistory = cardiacHistory;
            return this;
        }

        public Builder withSymptomsSystem(Map<MedicalCategory, Boolean> symptomsSystem) {
            chart.symptomsSystem = symptomsSystem;
            return this;
        }

        public Builder withBmi(Double bmi) {
            chart.bmi = bmi;
            return this;
        }

        public Builder withNormalExamChecks(NormalExamCheck normalExamCheck) {
            chart.normalExamCheck = normalExamCheck;
            return this;
        }

        public Builder withSurgicalRisk(SurgicalRisk surgicalRisk) {
            chart.surgicalRisk = surgicalRisk;
            return this;
        }

        public Builder withCardiacRiskAssessment(CardiacRiskAssessment cardiacRiskAssessment) {
            chart.cardiacRiskAssessment = cardiacRiskAssessment;
            return this;
        }

        public PreMedicalAssessmentChart build() {
            return new PreMedicalAssessmentChart(this);
        }
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = Instant.now();
    }


}
