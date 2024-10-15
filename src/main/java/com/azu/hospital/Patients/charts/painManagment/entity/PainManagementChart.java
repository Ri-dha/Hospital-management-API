  package com.azu.hospital.Patients.charts.painManagment.entity;

import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.physical_assessment_enm.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
  import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Table(name = "pain_management_chart")
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class PainManagementChart extends BaseCharts {

    private String chartName = "Pain Management Chart";

    private PainFeel painFeel;

    @Column(columnDefinition = "TEXT")
    private String painFeelRadiating;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean painGoAnywhereElse;

    @Column(columnDefinition = "TEXT")
    private String majorLifeChanges;

    private PainStatus painStatus;

    private String painStarted;

    private PainStartDetails painStartDetails;

    private PainWorseEnum painWorse;

    private String painWorseOther;

    private PainWorseEnum painBetter;

    private String painBetterOther;

    private DayTimes timePainGetWorse;

    private String timePainGetWorseOther;

    private String painDescribes;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean painInterruptSleep;

    @ElementCollection
    private Map<TreatmentType, Boolean> triedTreatments;

    @ElementCollection
    private Map<DiagnosticType, Boolean> lastYearTest;

    private String otherLastYearTest;

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

    public PainManagementChart() {
    }

    public PainManagementChart(Builder builder) {
      this.painFeel = builder.chart.painFeel;
      this.painFeelRadiating = builder.chart.painFeelRadiating;
      this.painGoAnywhereElse = builder.chart.painGoAnywhereElse;
      this.majorLifeChanges = builder.chart.majorLifeChanges;
      this.painStatus = builder.chart.painStatus;
      this.painStarted = builder.chart.painStarted;
      this.painStartDetails = builder.chart.painStartDetails;
      this.painWorse = builder.chart.painWorse;
      this.painWorseOther = builder.chart.painWorseOther;
      this.painBetter = builder.chart.painBetter;
      this.painBetterOther = builder.chart.painBetterOther;
      this.timePainGetWorse = builder.chart.timePainGetWorse;
      this.timePainGetWorseOther = builder.chart.timePainGetWorseOther;
      this.painDescribes = builder.chart.painDescribes;
      this.painInterruptSleep = builder.chart.painInterruptSleep;
      this.triedTreatments = builder.chart.triedTreatments;
      this.lastYearTest = builder.chart.lastYearTest;
      this.otherLastYearTest = builder.chart.otherLastYearTest;
    }

    public static class Builder {
      private final PainManagementChart chart = new PainManagementChart();

      public Builder withPainFeel(PainFeel painFeel) {
        chart.painFeel = painFeel;
        return this;
      }

      public Builder withPainFeelRadiating(String painFeelRadiating) {
        chart.painFeelRadiating = painFeelRadiating;
        return this;
      }

      public Builder withPainGoAnywhereElse(Boolean painGoAnywhereElse) {
        chart.painGoAnywhereElse = painGoAnywhereElse;
        return this;
      }

      public Builder withMajorLifeChanges(String majorLifeChanges) {
        chart.majorLifeChanges = majorLifeChanges;
        return this;
      }

      public Builder withPainStatus(PainStatus painStatus) {
        chart.painStatus = painStatus;
        return this;
      }

      public Builder withPainStarted(String painStarted) {
        chart.painStarted = painStarted;
        return this;
      }

      public Builder withPainStartDetails(PainStartDetails painStartDetails) {
        chart.painStartDetails = painStartDetails;
        return this;
      }

      public Builder withPainWorse(PainWorseEnum painWorse) {
        chart.painWorse = painWorse;
        return this;
      }

      public Builder withPainWorseOther(String painWorseOther) {
        chart.painWorseOther = painWorseOther;
        return this;
      }

      public Builder withPainBetter(PainWorseEnum painBetter) {
        chart.painBetter = painBetter;
        return this;
      }

      public Builder withPainBetterOther(String painBetterOther) {
        chart.painBetterOther = painBetterOther;
        return this;
      }

      public Builder withTimePainGetWorse(DayTimes timePainGetWorse) {
        chart.timePainGetWorse = timePainGetWorse;
        return this;
      }

      public Builder withTimePainGetWorseOther(String timePainGetWorseOther) {
        chart.timePainGetWorseOther = timePainGetWorseOther;
        return this;
      }

      public Builder withPainDescribes(String painDescribes) {
        chart.painDescribes = painDescribes;
        return this;
      }

      public Builder withPainInterruptSleep(Boolean painInterruptSleep) {
        chart.painInterruptSleep = painInterruptSleep;
        return this;
      }

      public Builder withTriedTreatments(Map<TreatmentType, Boolean> triedTreatments) {
        chart.triedTreatments = triedTreatments;
        return this;
      }

      public Builder withLastYearTest(Map<DiagnosticType, Boolean> lastYearTest) {
        chart.lastYearTest = lastYearTest;
        return this;
      }

      public Builder withOtherLastYearTest(String otherLastYearTest) {
        chart.otherLastYearTest = otherLastYearTest;
        return this;
      }

      public PainManagementChart build() {
        return new PainManagementChart(this);
      }
    }


  }
