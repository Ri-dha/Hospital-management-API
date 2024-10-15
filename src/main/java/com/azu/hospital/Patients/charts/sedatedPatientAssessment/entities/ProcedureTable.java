package com.azu.hospital.Patients.charts.sedatedPatientAssessment.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Table(name = "sedated_patient_assessment_procedure_table")
@Entity
@Getter
@Setter
public class ProcedureTable {
  @Id
  @SequenceGenerator(
          name = "sedated_patient_assessment_procedure_sequence",
          sequenceName = "sedated_patient_assessment_procedure_sequence"
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "sedated_patient_assessment_procedure_sequence"
  )
  private Long id;

  private String time;

  private String dose;

  private String drug;

//  @ManyToOne
//  @JoinColumn(name = "chart_id", referencedColumnName = "id")
//  private SedatedPatientAssessment chart;

  public ProcedureTable() {
  }

  public ProcedureTable(
          Long id,
          String time,
          String dose,
          String drug
//          SedatedPatientAssessment chart
  ) {
    this.id = id;
    this.time = time;
    this.dose = dose;
    this.drug = drug;
//    this.chart = chart;
  }
}
