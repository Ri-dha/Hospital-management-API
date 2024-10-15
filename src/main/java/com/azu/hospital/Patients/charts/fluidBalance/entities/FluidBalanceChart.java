package com.azu.hospital.Patients.charts.fluidBalance.entities;

import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
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

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "fluid_balance_chart")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class FluidBalanceChart extends BaseCharts {

  private String chartName = "Fluid Balance Chart";


  @Pattern(
          regexp = "\\d{4}-\\d{2}-\\d{2}",
          message = "Date Format Should be Like YYYY-MM-DD"
  )
  @NotNull(message = "Date Format Required")
  @NotBlank(message = "Date Format Required")
  @NotEmpty(message = "Date Format Required")
  private String dateFrom;

  @Pattern(
          regexp = "\\d{4}-\\d{2}-\\d{2}",
          message = "Date Format Should be Like YYYY-MM-DD"
  )
  @NotNull(message = "Date Format Required")
  @NotBlank(message = "Date Format Required")
  @NotEmpty(message = "Date Format Required")
  private String dateTo;

  @OneToMany(cascade = CascadeType.ALL)
  private List<FluidIntake> inputs;

  @OneToMany(cascade = CascadeType.ALL)
  private List<FluidOutput> outputs;

  @NotNull(message = "Total Required")
  private BigDecimal ml24InputHour;

  @NotNull(message = "Total Required")
  private BigDecimal fluidBalance24InputHour; // error

  @NotNull(message = "candidate Required")
  @NotBlank(message = "candidate Required")
  @NotEmpty(message = "candidate Required")
  private String candidate;


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


  public FluidBalanceChart(){

  }
  public FluidBalanceChart(Builder builder){
    this.dateFrom = builder.chart.dateFrom;
    this.dateTo = builder.chart.dateTo;
    this.ml24InputHour = builder.chart.ml24InputHour;
    this.fluidBalance24InputHour = builder.chart.fluidBalance24InputHour;
    this.candidate = builder.chart.candidate;
  }


  public static class Builder{
    private final FluidBalanceChart chart = new FluidBalanceChart();

    public Builder withDateFrom(String dateFrom) {
      chart.dateFrom = dateFrom;
      return this;
    }
    public Builder withDateTo(String dateTo) {
      chart.dateTo = dateTo;
      return this;
    }
    public Builder withMl24InputHour(BigDecimal ml24InputHour) {
      chart.ml24InputHour = ml24InputHour;
      return this;
    }
    public Builder withFluidBalance24InputHour(BigDecimal fluidBalance24InputHour) {
      chart.fluidBalance24InputHour = fluidBalance24InputHour;
      return this;
    }
    public Builder withCandidate(String candidate) {
      chart.candidate = candidate;
      return this;
    }
    public FluidBalanceChart build(){
      return new FluidBalanceChart(this);
    }
  }


}
