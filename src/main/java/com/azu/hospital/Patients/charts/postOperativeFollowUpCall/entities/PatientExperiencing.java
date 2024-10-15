package com.azu.hospital.Patients.charts.postOperativeFollowUpCall.entities;

import com.azu.hospital.Patients.charts.physical_assessment_enm.PatientExperiencingEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Table(name = "post_operative_follow_up_call_patient_experiencing")
@Entity
@Getter
@Setter
public class PatientExperiencing {
  @Id
  @SequenceGenerator(
          name = "patient_experiencing_sequence",
          sequenceName = "patient_experiencing_sequence"
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "patient_experiencing_sequence"
  )
  private Long id;

  private PatientExperiencingEnum experience;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean isChecked;

  @Column(columnDefinition = "TEXT")
  private String note;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "post_operative_follow_up_call")
  private PostOperativeFollowUpCall postOperativeFollowUpCall;

  public PatientExperiencing() {
  }

  public PatientExperiencing(
          Long id,
          PatientExperiencingEnum experience,
          Boolean isChecked,
          String note
  ) {
    this.id = id;
    this.experience = experience;
    this.isChecked = isChecked;
    this.note = note;
  }

  public PatientExperiencing(
          PatientExperiencingEnum experience,
          Boolean isChecked,
          String note
  ) {
    this.experience = experience;
    this.isChecked = isChecked;
    this.note = note;
  }
}
