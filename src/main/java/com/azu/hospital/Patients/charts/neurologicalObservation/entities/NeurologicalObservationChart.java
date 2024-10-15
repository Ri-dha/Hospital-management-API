package com.azu.hospital.Patients.charts.neurologicalObservation.entities;


import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = true)
@Table(name = "neurological_observation_chart")
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class NeurologicalObservationChart extends BaseCharts {

  private String chartName = "Neurological Observation Chart";

  private String date;

  private String candidateName;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "neurological_observation_adult_id", referencedColumnName = "id")
  private AdultEntity adult;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "neurological_observation_child_id", referencedColumnName = "id")
  private ChildEntity child;

  @Column(columnDefinition = "TEXT")
  private String note;

  @CreatedBy
  @Column(
          updatable = false
  )
  private Long createdBy;
  @org.springframework.data.annotation.LastModifiedBy
  @Column(
          insertable = false
  )
  private Long LastModifiedBy;



  public NeurologicalObservationChart() {
  }

  public NeurologicalObservationChart(Builder builder) {
    this.date = builder.chart.date;
    this.candidateName = builder.chart.candidateName;
    this.adult = builder.chart.adult;
    this.child = builder.chart.child;
    this.note = builder.chart.note;
  }

  public static class Builder{
    private final NeurologicalObservationChart chart = new NeurologicalObservationChart();

    public Builder withDate(String date){
      chart.date = date;
      return this;
    }
    public Builder withCandidateName(String candidateName){
      chart.candidateName = candidateName;
      return this;
    }
    public Builder withAdult(AdultEntity adult){
      chart.adult = adult;
      return this;
    }
    public Builder withChild(ChildEntity child){
      chart.child = child;
      return this;
    }
    public Builder withNote(String note){
      chart.note = note;
      return this;
    }
    public NeurologicalObservationChart build(){
      return new NeurologicalObservationChart(this);
    }
  }

}
