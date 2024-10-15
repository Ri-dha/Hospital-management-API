package com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "patient_meds_per_md")
@Getter
@Setter
public class MedsPerMDEntity {
  @Id
  @SequenceGenerator(
          name = "meds_per_md_sequence",
          sequenceName = "meds_per_md_sequence"
  )
  @GeneratedValue(
          generator = "meds_per_md_sequence",
          strategy = GenerationType.SEQUENCE
  )
  private Long medId;



  private String observationNotes;

  private String routeOfAdministhation;

  private Integer timingOfAdministhation;

  @Pattern(
          regexp = "\\d{4}-\\d{2}-\\d{2}",
          message = "Date Should be Like YYYY-MM-DD"
  )
  @NotNull(message = "Date is Required")
  @NotBlank(message = "Date is Required")
  @NotEmpty(message = "Date is Required")
  private String date;

  @Pattern(
          regexp = "^(?:[01]\\d|2[0-3]):[0-5]\\d$",
          message = "Invalid time format"
  )
  @NotNull(message = "Time is Required")
  @NotBlank(message = "Time is Required")
  @NotEmpty(message = "Time is Required")
  private String time;

  public MedsPerMDEntity() {
  }

  public MedsPerMDEntity(
          Long medId,
          String observationNotes,
          String routeOfAdministhation,
          Integer timingOfAdministhation,
          String date,
          String time
  ) {
    this.medId = medId;
    this.observationNotes = observationNotes;
    this.routeOfAdministhation = routeOfAdministhation;
    this.timingOfAdministhation = timingOfAdministhation;
    this.date = date;
    this.time = time;
  }
}
