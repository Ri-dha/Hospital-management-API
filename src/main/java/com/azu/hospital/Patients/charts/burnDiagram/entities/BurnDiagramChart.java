package com.azu.hospital.Patients.charts.burnDiagram.entities;

import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "burn_diagram_chart")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class BurnDiagramChart extends BaseCharts {
  private String chartName = "Burn Diagram Chart";

  private String dateOfBurn;

  private String dateOfAdmission;

  private String dateOfTbsaEstimationPreliminary;

  private String preliminaryDoctorName;

  private String dateOfTbsaEstimationFinal;

  private String finalDoctorName;

  private Float superficialDermal;

  private Float midDermal;

  private Float deepDermal;

  private Float midDermalFluidLoos;

  private Float deepDermalFluidLoss;

  private Float finalTbsa;

  private Float totalFluidLoos;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private BurnBodyDiagram burnBodyDiagram;


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



  public BurnDiagramChart() {
  }
  public BurnDiagramChart(Builder builder) {
    this.dateOfBurn = builder.chart.dateOfBurn;
    this.dateOfAdmission = builder.chart.dateOfAdmission;
    this.dateOfTbsaEstimationPreliminary = builder.chart.dateOfTbsaEstimationPreliminary;
    this.preliminaryDoctorName = builder.chart.preliminaryDoctorName;
    this.dateOfTbsaEstimationFinal = builder.chart.dateOfTbsaEstimationFinal;
    this.finalDoctorName = builder.chart.finalDoctorName;
    this.superficialDermal = builder.chart.superficialDermal;
    this.midDermal = builder.chart.midDermal;
    this.deepDermal = builder.chart.deepDermal;
    this.midDermalFluidLoos = builder.chart.midDermalFluidLoos;
    this.deepDermalFluidLoss = builder.chart.deepDermalFluidLoss;
    this.finalTbsa = builder.chart.finalTbsa;
    this.totalFluidLoos = builder.chart.totalFluidLoos;
    this.burnBodyDiagram = builder.chart.burnBodyDiagram;
  }

  public static class Builder {
    private final BurnDiagramChart chart = new BurnDiagramChart();

    public Builder withDateOfBurn(String dateOfBurn){
      chart.dateOfBurn = dateOfBurn;
      return this;
    }
    public Builder withDateOfAdmission(String dateOfAdmission){
      chart.dateOfAdmission = dateOfAdmission;
      return this;
    }
    public Builder withDateOfTbsaEstimationPreliminary(String dateOfTbsaEstimationPreliminary){
      chart.dateOfTbsaEstimationPreliminary = dateOfTbsaEstimationPreliminary;
      return this;
    }
    public Builder withPreliminaryDoctorName(String preliminaryDoctorName){
      chart.preliminaryDoctorName = preliminaryDoctorName;
      return this;
    }
    public Builder withDateOfTbsaEstimationFinal(String dateOfTbsaEstimationFinal){
      chart.dateOfTbsaEstimationFinal = dateOfTbsaEstimationFinal;
      return this;
    }
    public Builder withFinalDoctorName(String finalDoctorName){
      chart.finalDoctorName = finalDoctorName;
      return this;
    }
    public Builder withSuperficialDermal(Float superficialDermal){
      chart.superficialDermal = superficialDermal;
      return this;
    }
    public Builder withMidDermal(Float midDermal){
      chart.midDermal = midDermal;
      return this;
    }
    public Builder withDeepDermal(Float deepDermal){
      chart.deepDermal = deepDermal;
      return this;
    }
    public Builder withMidDermalFluidLoos(Float midDermalFluidLoos){
      chart.midDermalFluidLoos = midDermalFluidLoos;
      return this;
    }
    public Builder withDeepDermalFluidLoss(Float deepDermalFluidLoss){
      chart.deepDermalFluidLoss = deepDermalFluidLoss;
      return this;
    }
    public Builder withFinalTbsa(Float finalTbsa){
      chart.finalTbsa = finalTbsa;
      return this;
    }
    public Builder withTotalFluidLoos(Float totalFluidLoos){
      chart.totalFluidLoos = totalFluidLoos;
      return this;
    }
    public Builder withBurnBodyDiagram(BurnBodyDiagram burnBodyDiagram){
      chart.burnBodyDiagram = burnBodyDiagram;
      return this;
    }
    public BurnDiagramChart build(){
      return new BurnDiagramChart(this);
    }
  }


}
