package com.azu.hospital.Patients.charts.preAnestheticEvaluation.entity;

import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.physical_assessment_enm.Plan;
import com.azu.hospital.Patients.charts.physical_assment.AsaStatus;
import com.azu.hospital.Patients.charts.physical_assment.LabsECG;
import com.azu.hospital.Patients.charts.physical_assment.PhysicalExam;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "pre_anesthetic_evaluation_chart")
@DynamicUpdate
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class PreAnestheticEvaluationChart extends BaseCharts {

    private String chartName = "Pre Anesthetic Evaluation Chart";

    private String proposedProcedure;
    // TODO => provide [Past Medical History, Medications/Supplements, Allergy, Past Surgical History] by relation
    @Embedded
    private PhysicalExam physicalExam;
    // TODO => provide Labs/ECG, ASA Status by relation

    @Embedded
    private LabsECG labEcg;

    @Embedded
    private AsaStatus asa;

    private Plan plan;

    private String anesthesiologist;

    private String date;

    private String time;

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
    public PreAnestheticEvaluationChart() {
    }

    public PreAnestheticEvaluationChart(Builder builder) {
        this.proposedProcedure = builder.chart.proposedProcedure;
        this.physicalExam = builder.chart.physicalExam;
        this.labEcg = builder.chart.labEcg;
        this.asa = builder.chart.asa;
        this.plan = builder.chart.plan;
        this.anesthesiologist = builder.chart.anesthesiologist;
        this.date = builder.chart.date;
        this.time = builder.chart.time;
    }



    public static class Builder{
        private final PreAnestheticEvaluationChart chart = new PreAnestheticEvaluationChart();
        public Builder withProposedProcedure(String proposedProcedure){
            chart.proposedProcedure = proposedProcedure;
            return this;
        }
        public Builder withLabsEcg(LabsECG labsEcg){
            chart.labEcg = labsEcg;
            return this;
        }
        public Builder withAsaStatus(AsaStatus asa){
            chart.asa = asa;
            return this;
        }
        public Builder withAnesthesiologist(String anesthesiologist){
            chart.anesthesiologist = anesthesiologist;
            return this;
        }
        public Builder withDate(String date){
            chart.date = date;
            return this;
        }
        public Builder withTime(String time){
            chart.time = time;
            return this;
        }
        public Builder withPhysicalExam(PhysicalExam physicalExam){
            chart.physicalExam = physicalExam;
            return this;
        }
        public Builder withPlan(Plan plan){
            chart.plan = plan;
            return this;
        }
        public PreAnestheticEvaluationChart build(){
            return new PreAnestheticEvaluationChart(this);
        }
    }

}
