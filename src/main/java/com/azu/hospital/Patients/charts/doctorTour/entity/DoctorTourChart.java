package com.azu.hospital.Patients.charts.doctorTour.entity;

import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.physical_assessment_enm.DoctorTourShift;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "doctor_tour_chart")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class DoctorTourChart extends BaseCharts {

  private String chartName = "Doctor Tour Chart";

  @Pattern(
          regexp = "\\d{4}-\\d{2}-\\d{2}",
          message = "Date Should be Like YYYY-MM-DD"
  )
  @NotNull(message = "Date is Required")
  @NotBlank(message = "Date is Required")
  @NotEmpty(message = "Date is Required")
  private String date;

  @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Time format should be like HH:MM")
  @NotNull(message = "Time is Required")
  @NotBlank(message = "Time is Required")
  @NotEmpty(message = "Time is Required")
  private String time;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isMaskOn ;

  private DoctorTourShift shift;

  @NotNull(message = "BMI required")
  private Double bmi;

  @Column(columnDefinition = "TEXT")
  @NotNull(message = "Medical Diagnosis is required")
  @NotBlank(message = "Medical Diagnosis is required")
  @NotEmpty(message = "Medical Diagnosis is required")
  private String medicalDx;

  @Column(columnDefinition = "TEXT")
  @NotNull(message = "Details is required")
  @NotBlank(message = "Details is required")
  @NotEmpty(message = "Details is required")
  private String tourDetails;

  @Column(columnDefinition = "TEXT")
  private String followUpNote;


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




  public DoctorTourChart() {
  }

  public DoctorTourChart(Builder builder) {
    this.date = builder.chart.date;
    this.time = builder.chart.time;
    this.bmi = builder.chart.bmi;
    this.medicalDx = builder.chart.medicalDx;
    this.tourDetails = builder.chart.tourDetails;
    this.followUpNote = builder.chart.followUpNote;
    this.isMaskOn = builder.chart.isMaskOn;
    this.shift  = builder.chart.shift;
  }

  public static class Builder{
    private final DoctorTourChart chart = new DoctorTourChart();

    public Builder withDate(String date){
      chart.date = date;
      return this;
    }
    public Builder withTime(String time){
      chart.time = time;
      return this;
    }
    public Builder withBmi(Double bmi){
      chart.bmi = bmi;
      return this;
    }
    public Builder withMedicalDx(String medicalDx){
      chart.medicalDx = medicalDx;
      return this;
    }
    public Builder withTourDetails(String tourDetails){
      chart.tourDetails = tourDetails;
      return this;
    }
    public Builder withFollowUpNote(String followUpNote){
      chart.followUpNote = followUpNote;
      return this;
    }

    public Builder withIsMaskOn(Boolean isMaskOn){
      chart.isMaskOn = isMaskOn;
      return this;
    }

    public Builder withShift(DoctorTourShift shift){
      chart.shift = shift;
      return this;
    }
    public DoctorTourChart build(){
      return new DoctorTourChart(this);
    }
  }
  
}
