package com.azu.hospital.Patients.charts.discharge.entity;

import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.utils.files.File;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Role;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Table(name = "discharge_chart")
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class DischargeChart extends BaseCharts {

    private String chartName = "Discharge Chart";

    @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "Discharge Date Should be Like 2021-12-12"
    )
    private String dischargedDate;

    @Pattern(
            regexp = "\\d{2}:\\d{2}",
            message = "Discharge Time Should be Like 22:11"
    )
    private String dischargeTime;

    @Column(columnDefinition = "TEXT")
    private String treatmentOnDischarge;

    @Column(columnDefinition = "TEXT")
    private String recommendation;

    @Column(columnDefinition = "TEXT")
    private String surgicalMedicalProcedure;


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









    public DischargeChart() {
    }

    public DischargeChart(Builder builder) {
        this.dischargedDate = builder.chart.dischargedDate;
        this.treatmentOnDischarge = builder.chart.treatmentOnDischarge;
        this.recommendation = builder.chart.recommendation;
        this.surgicalMedicalProcedure = builder.chart.surgicalMedicalProcedure;
        this.dischargeTime = builder.chart.dischargeTime;

    }

    public static class Builder {
        private final DischargeChart chart = new DischargeChart();

        public Builder withDischargedDate(String dischargedDate) {
            chart.dischargedDate = dischargedDate;
            return this;
        }
        public Builder withDischargedTime(String dischargeTime) {
            chart.dischargeTime = dischargeTime;
            return this;
        }

        public Builder withSurgicalMedicalProcedure(String surgicalMedicalProcedure) {
            chart.surgicalMedicalProcedure = surgicalMedicalProcedure;
            return this;
        }

        public Builder withTreatmentOnDischarge(String treatmentOnDischarge) {
            chart.treatmentOnDischarge = treatmentOnDischarge;
            return this;
        }
        public Builder withRecommendation(String recommendation) {
            chart.recommendation = recommendation;
            return this;
        }

        public DischargeChart build() {
            return new DischargeChart(this);
        }
    }
}
