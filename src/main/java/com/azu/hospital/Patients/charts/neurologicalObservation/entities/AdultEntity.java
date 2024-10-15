package com.azu.hospital.Patients.charts.neurologicalObservation.entities;

import com.azu.hospital.Patients.charts.physical_assessment_enm.Arms;
import com.azu.hospital.Patients.charts.physical_assessment_enm.Legs;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.Instant;

@Table(name = "neurological_observation_adult")
@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AdultEntity {
  @Id
  @SequenceGenerator(
          name = "neurological_observation_adult_sequence",
          sequenceName = "neurological_observation_adult_sequence"
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "neurological_observation_adult_sequence"
  )
  private Long id;

  @Range(min = 0, max = 4, message = "The Eye Opening value must be between 1 and 4")
  private Integer eyeOpening;

  @Range(min = 0, max = 4, message = "The Verbal Response value must be between 1 and 4")
  private Integer verbalResponse;

  @Range(min = 0, max = 4, message = "The Best Motor Response value must be between 1 and 4")
  private Integer motorResponse;

  @Range(min = 1, max = 7, message = "The Left Pupil value must be between 1 and 7")
  private Integer leftPupil;

  @Range(min = 1, max = 7, message = "The Right Pupil value must be between 1 and 7")
  private Integer rightPupil;

  private Legs legs;

  @Enumerated(EnumType.STRING)
  private Arms arms;

  @OneToOne
  private NeurologicalObservationChart chart;

  @Column(updatable = false)
  private Instant createdAt;
@Column(insertable = false)
  private Instant updatedAt;

  public AdultEntity() {
  }

  public AdultEntity(
          Long id,
          Integer eyeOpening,
          Integer verbalResponse,
          Integer motorResponse,
          Integer leftPupil,
          Integer rightPupil,
          Legs legs,
          Arms arms,
          NeurologicalObservationChart chart
  ) {
    this.id = id;
    this.eyeOpening = eyeOpening;
    this.verbalResponse = verbalResponse;
    this.motorResponse = motorResponse;
    this.leftPupil = leftPupil;
    this.rightPupil = rightPupil;
    this.legs = legs;
    this.arms = arms;
    this.chart = chart;
  }



  @PrePersist
  public void onCreate(){
    this.createdAt = Instant.now();
  }
  @PreUpdate
  public void onUpdate(){
    this.updatedAt = Instant.now();
  }
}
