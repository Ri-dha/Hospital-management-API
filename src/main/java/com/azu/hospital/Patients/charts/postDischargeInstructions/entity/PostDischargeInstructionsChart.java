package com.azu.hospital.Patients.charts.postDischargeInstructions.entity;

import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;
@EqualsAndHashCode(callSuper = true)
@Table(name = "post_discharge_instructions_chart")
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class PostDischargeInstructionsChart extends BaseCharts {

  private String chartName = "Post Discharge Instructions Chart";
  private String anticoagulantAdvisement;

  private String doctorName;

  private String phoneNumber;

  private String hospitalName;

  private String patientSignature;

  private String patientSignatureDate;

  private String patientSignatureTime;

  private String registeredNurseName;

  private String registeredDate;

  private String registeredTime;

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


  public PostDischargeInstructionsChart() {
  }

  public PostDischargeInstructionsChart(Builder builder) {
    this.anticoagulantAdvisement = builder.chart.anticoagulantAdvisement;
    this.doctorName = builder.chart.doctorName;
    this.phoneNumber = builder.chart.phoneNumber;
    this.hospitalName = builder.chart.hospitalName;
    this.patientSignature = builder.chart.patientSignature;
    this.patientSignatureDate = builder.chart.patientSignatureDate;
    this.patientSignatureTime = builder.chart.patientSignatureTime;
    this.registeredNurseName = builder.chart.registeredNurseName;
    this.registeredDate = builder.chart.registeredDate;
    this.registeredTime = builder.chart.registeredTime;
  }


  public static class Builder{
    private final PostDischargeInstructionsChart chart = new PostDischargeInstructionsChart();

    public Builder withAnticoagulantAdvisement(String anticoagulantAdvisement){
      chart.anticoagulantAdvisement = anticoagulantAdvisement;
      return this;
    }
    public Builder withDoctorName(String doctorName){
      chart.doctorName = doctorName;
      return this;
    }
    public Builder withPhoneNumber(String phoneNumber){
      chart.phoneNumber = phoneNumber;
      return this;
    }
    public Builder withHospitalName(String hospitalName){
      chart.hospitalName = hospitalName;
      return this;
    }
    public Builder withPatientSignature(String patientSignature){
      chart.patientSignature = patientSignature;
      return this;
    }
    public Builder withPatientSignatureDate(String patientSignatureDate){
      chart.patientSignatureDate = patientSignatureDate;
      return this;
    }
    public Builder withPatientSignatureTime(String patientSignatureTime){
      chart.patientSignatureTime = patientSignatureTime;
      return this;
    }
    public Builder withRegisteredNurseName(String registeredNurseName){
      chart.registeredNurseName = registeredNurseName;
      return this;
    }
    public Builder withRegisteredDate(String registeredDate){
      chart.registeredDate = registeredDate;
      return this;
    }
    public Builder withRegisteredTime(String registeredTime){
      chart.registeredTime = registeredTime;
      return this;
    }
    public PostDischargeInstructionsChart build(){
      return new PostDischargeInstructionsChart(this);
    }
  }

}
