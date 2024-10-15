package com.azu.hospital.Patients.charts.preOperativeHPChart.entity;


import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.physical_assessment_enm.HealthCondition;
import com.azu.hospital.Patients.charts.physical_assessment_enm.MedicalCondition;
import com.azu.hospital.Patients.charts.physical_assment.Habit;
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
@Table(name = "pre_operative_hp_chart")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class PreOperativeHPChart extends BaseCharts {


    private String chartName = "Pre Operative HP Chart";
  @Column(columnDefinition = "TEXT")
  private String diagnosisChiefComplaint;

  @Column(columnDefinition = "TEXT")
  private String pastMedicalHistory;

  @Column(columnDefinition = "TEXT")
  private String familyPhysician;

  @Column(columnDefinition = "TEXT")
  private String surgicalHistory;

  @Column(columnDefinition = "TEXT")
  private String medications;

  @ElementCollection(fetch = FetchType.EAGER)
  private Map<Habit, Boolean> habit;

  @ElementCollection
  private Map<MedicalCondition, Boolean> chronicDisease;

  @ElementCollection
  private Map<HealthCondition, Boolean> familyHistory;

  // TODO: 9/11/2023 discussions later relation with patient information
  private String general;

  private String headNeck;

  private String chest;

  private String heart;

  private String lungs;

  private String abdomen;

  private String skinOfExtremities;

  private String impression;

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

  public PreOperativeHPChart() {
  }

  public PreOperativeHPChart(Builder builder) {
    this.diagnosisChiefComplaint = builder.chart.diagnosisChiefComplaint;
    this.pastMedicalHistory = builder.chart.pastMedicalHistory;
    this.familyPhysician = builder.chart.familyPhysician;
    this.surgicalHistory = builder.chart.surgicalHistory;
    this.medications = builder.chart.medications;
    this.habit = builder.chart.habit;
    this.chronicDisease = builder.chart.chronicDisease;
    this.familyHistory = builder.chart.familyHistory;
    this.general = builder.chart.general;
    this.headNeck = builder.chart.headNeck;
    this.chest = builder.chart.chest;
    this.heart = builder.chart.heart;
    this.lungs = builder.chart.lungs;
    this.abdomen = builder.chart.abdomen;
    this.skinOfExtremities = builder.chart.skinOfExtremities;
    this.impression = builder.chart.impression;
  }

  public static class Builder {
    private final PreOperativeHPChart chart = new PreOperativeHPChart();

    public Builder withDiagnosisChiefComplaint(String diagnosisChiefComplaint) {
      chart.diagnosisChiefComplaint = diagnosisChiefComplaint;
      return this;
    }

    public Builder withPastMedicalHistory(String pastMedicalHistory) {
      chart.pastMedicalHistory = pastMedicalHistory;
      return this;
    }

    public Builder withFamilyPhysician(String familyPhysician) {
      chart.familyPhysician = familyPhysician;
      return this;
    }

    public Builder withSurgicalHistory(String surgicalHistory) {
      chart.surgicalHistory = surgicalHistory;
      return this;
    }

    public Builder withMedications(String medications) {
      chart.medications = medications;
      return this;
    }

    public Builder withHabit(Map<Habit, Boolean> habit) {
      chart.habit = habit;
      return this;
    }

    public Builder withChronicDisease(Map<MedicalCondition, Boolean> chronicDisease) {
      chart.chronicDisease = chronicDisease;
      return this;
    }

    public Builder withFamilyHistory(Map<HealthCondition, Boolean> familyHistory) {
      chart.familyHistory = familyHistory;
      return this;
    }

    public Builder withGeneral(String general) {
      chart.general = general;
      return this;
    }

    public Builder withHeadNeck(String headNeck) {
      chart.headNeck = headNeck;
      return this;
    }

    public Builder withChest(String chest) {
      chart.chest = chest;
      return this;
    }

    public Builder withHeart(String heart) {
      chart.heart = heart;
      return this;
    }

    public Builder withLungs(String lungs) {
      chart.lungs = lungs;
      return this;
    }

    public Builder withAbdomen(String abdomen) {
      chart.abdomen = abdomen;
      return this;
    }

    public Builder withSkinOfExtremities(String skinOfExtremities) {
      chart.skinOfExtremities = skinOfExtremities;
      return this;
    }

    public Builder withImpression(String impression) {
      chart.impression = impression;
      return this;
    }

    public PreOperativeHPChart build() {
      return new PreOperativeHPChart(this);
    }
  }

}
