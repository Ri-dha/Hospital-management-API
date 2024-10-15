package com.azu.hospital.Patients.charts.postAnestheticEvaluation.entity;

import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.physical_assessment_enm.PatientHas;
import com.azu.hospital.Patients.charts.physical_assessment_enm.PatientStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "post_anesthetic_evaluation_chart")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class  PostAnestheticEvaluationChart extends BaseCharts {

  private String chartName = "Post Anesthetic Evaluation Chart";

  private Boolean anesthesiaComplications;

  private PatientStatus patientStatus;

  private PatientHas patientHas;

  private String patientHasOther;

  private String postAnesthesiaNote;

  private String anesthesiologist;

  private String date;

  private String time;

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



  public PostAnestheticEvaluationChart() {
  }

  public PostAnestheticEvaluationChart(Builder builder) {
    this.anesthesiaComplications = builder.chart.anesthesiaComplications;
    this.patientStatus = builder.chart.patientStatus;
    this.patientHas = builder.chart.patientHas;
    this.patientHasOther = builder.chart.patientHasOther;
    this.postAnesthesiaNote = builder.chart.postAnesthesiaNote;
    this.anesthesiologist = builder.chart.anesthesiologist;
    this.date = builder.chart.date;
    this.time = builder.chart.time;
  }

  public static class Builder {
    private final PostAnestheticEvaluationChart chart = new PostAnestheticEvaluationChart();

    public Builder withAnesthesiaComplications(Boolean anesthesiaComplications) {
      chart.anesthesiaComplications = anesthesiaComplications;
      return this;
    }

    public Builder withPatientStatus(PatientStatus patientStatus) {
      chart.patientStatus = patientStatus;
      return this;
    }

    public Builder withPatientHas(PatientHas patientHas) {
      chart.patientHas = patientHas;
      return this;
    }

    public Builder withPatientHasOther(String patientHasOther) {
      chart.patientHasOther = patientHasOther;
      return this;
    }

    public Builder withPostAnesthesiaNote(String postAnesthesiaNote) {
      chart.postAnesthesiaNote = postAnesthesiaNote;
      return this;
    }

    public Builder withAnesthesiologist(String anesthesiologist) {
      chart.anesthesiologist = anesthesiologist;
      return this;
    }

    public Builder withDate(String date) {
      chart.date = date;
      return this;
    }

    public Builder withTime(String time) {
      chart.time = time;
      return this;
    }

    public PostAnestheticEvaluationChart build() {
      return new PostAnestheticEvaluationChart(this);
    }
  }


}
