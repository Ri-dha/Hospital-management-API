package com.azu.hospital.Patients.charts.patientAssessment.entity;

import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.physical_assessment_enm.PreProcedureChecklist;
import com.azu.hospital.Patients.charts.physical_assessment_enm.SkinCondition;
import com.azu.hospital.Patients.charts.physical_assment.IVSite;
import com.azu.hospital.Patients.charts.physical_assment.PatientLOC;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "patient_assessment_chart")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class PatientAssessmentChart extends BaseCharts {

    private String chartName = "Patient Assessment Chart";

    private String diagnosis;
    private String procedures;
    private SkinCondition skinCondition;

    @ElementCollection
    private Map<String, Boolean> physicalLimitations;

    @Embedded
    private PatientLOC loc;

    @Embedded
    private IVSite ivSite;


    @ElementCollection
    private Map<PreProcedureChecklist, Boolean> preProcedureChecklist;

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

    public PatientAssessmentChart() {
    }

    public PatientAssessmentChart(Builder builder) {
        this.diagnosis = builder.chart.diagnosis;
        this.procedures = builder.chart.procedures;
        this.skinCondition = builder.chart.skinCondition;
        this.physicalLimitations = builder.chart.physicalLimitations;
        this.loc = builder.chart.loc;
        this.ivSite = builder.chart.ivSite;
        this.preProcedureChecklist = builder.chart.preProcedureChecklist;
    }

    public static class Builder {
        PatientAssessmentChart chart = new PatientAssessmentChart();

        public Builder withDiagnosis(String diagnosis) {
            chart.diagnosis = diagnosis;
            return this;
        }

        public Builder withProcedures(String procedures) {
            chart.procedures = procedures;
            return this;
        }

        public Builder withSkinCondition(SkinCondition skinCondition) {
            chart.skinCondition = skinCondition;
            return this;
        }

        public Builder withPhysicalLimitations(
                Map<String, Boolean> physicalLimitations
        ) {
            chart.physicalLimitations = physicalLimitations;
            return this;
        }

        public Builder withPatientLOC(
                PatientLOC loc
        ) {
            chart.loc = loc;
            return this;
        }

        public Builder withIVSite(
                IVSite ivSite
        ) {
            chart.ivSite = ivSite;
            return this;
        }
        public Builder withPreProcedureChecklist(Map<PreProcedureChecklist, Boolean> preProcedureChecklist){
            chart.preProcedureChecklist = preProcedureChecklist;
            return this;
        }

        public PatientAssessmentChart build() {
            return new PatientAssessmentChart(this);
        }
    }

    
}
