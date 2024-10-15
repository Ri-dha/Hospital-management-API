package com.azu.hospital.Patients.charts.preAdvanceDirective.entity;

import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "pre_advance_directive_chart")
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class PreAdvanceDirectiveChart extends BaseCharts {

  private String chartName = "Pre Advance Directive Chart";
  @Pattern(regexp = "OPTION_1|OPTION_2", message = "Invalid choice. Please select Option 1 or Option 2.")
  private String option;

  private String printName;

  private String signature;

  private String date;

  private String preOpNurseSignature;

  private String preOpNurseDate;

  private String preOpNurseTime;

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

  public PreAdvanceDirectiveChart() {
  }
  public PreAdvanceDirectiveChart(Builder builder) {
    this.option = builder.chart.option;
    this.printName = builder.chart.printName;
    this.signature = builder.chart.signature;
    this.date = builder.chart.date;
    this.preOpNurseSignature = builder.chart.preOpNurseSignature;
    this.preOpNurseDate = builder.chart.preOpNurseDate;
    this.preOpNurseTime = builder.chart.preOpNurseTime;
  }

  public static class Builder{
    private final PreAdvanceDirectiveChart chart = new PreAdvanceDirectiveChart();

    public Builder withOption(String option){
      chart.option = option;
      return this;
    }
    public Builder withPrintName(String printName){
      chart.printName = printName;
      return this;
    }
    public Builder withSignature(String signature){
      chart.signature = signature;
      return this;
    }
    public Builder withDate(String date){
      chart.date = date;
      return this;
    }
    public Builder withPreOpNurseSignature(String preOpNurseSignature){
      chart.preOpNurseSignature = preOpNurseSignature;
      return this;
    }
    public Builder withPreOpNurseDate(String preOpNurseDate){
      chart.preOpNurseDate = preOpNurseDate;
      return this;
    }
    public Builder withPreOpNurseTime(String preOpNurseTime){
      chart.preOpNurseTime = preOpNurseTime;
      return this;
    }
    public PreAdvanceDirectiveChart build(){
      return new PreAdvanceDirectiveChart(this);
    }
  }

}
