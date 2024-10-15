package com.azu.hospital.Patients.charts.nusringCarePlan.entity;

import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity(name = "nusring")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class NursingCarePlan extends BaseCharts {

    private String chartName = "Nursing Care Plan";

    @Column(columnDefinition = "TEXT")
    private String nursingDiagnosis;


    @Column(columnDefinition = "TEXT")
    private String goalsAndOutComes;

    @Column(columnDefinition = "TEXT")
    private String intervensions;

    @Column(columnDefinition = "TEXT")
    private String evaluation;


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

    public NursingCarePlan() {
    }

    public NursingCarePlan(Builder builder) {
        this.nursingDiagnosis = builder.chart.nursingDiagnosis;
        this.goalsAndOutComes = builder.chart.goalsAndOutComes;
        this.intervensions = builder.chart.intervensions;
        this.evaluation = builder.chart.evaluation;

    }

    public static class Builder {
        private final NursingCarePlan chart = new NursingCarePlan();

        public Builder withNursingDiagnosis(String goalsAndOutComes) {
            chart.goalsAndOutComes = goalsAndOutComes;
            return this;
        }
        public Builder withGoalsAndOutComes(String nursingDiagnosis) {
            chart.nursingDiagnosis = nursingDiagnosis;
            return this;
        }
        public Builder withIntervensions(String intervensions) {
            chart.intervensions = intervensions;
            return this;
        }
        public Builder withEvaluation(String evaluation) {
            chart.evaluation = evaluation;
            return this;
        }
        public NursingCarePlan build(){
            return new NursingCarePlan(this);
        }
    }




}