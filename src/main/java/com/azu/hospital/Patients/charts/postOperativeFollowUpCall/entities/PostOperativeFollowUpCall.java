package com.azu.hospital.Patients.charts.postOperativeFollowUpCall.entities;

import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "post_operative_follow_up_call")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class PostOperativeFollowUpCall extends BaseCharts {

  private String dateOfCall;
  private String chartName = "Post Operative Follow Up Call Chart";

  private String lsmCaller;

  private Integer numberOfAttempts;

  private String time;

  private String procedure;

  private BigDecimal painLevel;

  private String lsmPhysicianSignature;

  private String anesthesiologistSignature;

  private String dateOfService;

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


  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "postOperativeFollowUpCall")
  private List<PatientExperiencing> patientExperiencing;
  public PostOperativeFollowUpCall() {
  }

  public PostOperativeFollowUpCall(Builder builder) {
    this.dateOfCall = builder.chart.dateOfCall;
    this.lsmCaller = builder.chart.lsmCaller;
    this.numberOfAttempts = builder.chart.numberOfAttempts;
    this.time = builder.chart.time;
    this.procedure = builder.chart.procedure;
    this.painLevel = builder.chart.painLevel;
    this.lsmPhysicianSignature = builder.chart.lsmPhysicianSignature;
    this.anesthesiologistSignature = builder.chart.anesthesiologistSignature;
    this.dateOfService = builder.chart.dateOfService;
    this.patientExperiencing = builder.chart.patientExperiencing;
  }

  public static class Builder {
    private final PostOperativeFollowUpCall chart = new PostOperativeFollowUpCall();

    public Builder withDateOfCall(String dateOfCall) {
      chart.dateOfCall = dateOfCall;
      return this;
    }

    public Builder withLsmCaller(String lsmCaller) {
      chart.lsmCaller = lsmCaller;
      return this;
    }

    public Builder withNumberOfAttempts(Integer numberOfAttempts) {
      chart.numberOfAttempts = numberOfAttempts;
      return this;
    }

    public Builder withTime(String time) {
      chart.time = time;
      return this;
    }

    public Builder withProcedure(String procedure) {
      chart.procedure = procedure;
      return this;
    }

    public Builder withPainLevel(BigDecimal painLevel) {
      chart.painLevel = painLevel;
      return this;
    }

    public Builder withLsmPhysicianSignature(String lsmPhysicianSignature) {
      chart.lsmPhysicianSignature = lsmPhysicianSignature;
      return this;
    }

    public Builder withAnesthesiologistSignature(String anesthesiologistSignature) {
      chart.anesthesiologistSignature = anesthesiologistSignature;
      return this;
    }

    public Builder withDateOfService(String dateOfService) {
      chart.dateOfService = dateOfService;
      return this;
    }
    public Builder withPatientExperiencing(List<PatientExperiencing> patientExperiencing){
      chart.patientExperiencing = patientExperiencing;
      return this;
    }

    public PostOperativeFollowUpCall build() {
      return new PostOperativeFollowUpCall(this) ;
    }
  }

}
