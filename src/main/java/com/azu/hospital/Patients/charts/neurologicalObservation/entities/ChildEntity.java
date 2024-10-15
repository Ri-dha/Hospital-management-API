package com.azu.hospital.Patients.charts.neurologicalObservation.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.Instant;

@Table(name = "neurological_observation_child")
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ChildEntity {
  @Id
  @SequenceGenerator(
          name = "neurological_observation_child_sequence",
          sequenceName = "neurological_observation_child_sequence"
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "neurological_observation_child_sequence"
  )
  private Long id;

  @Range(min = 0, max = 4, message = "The Appearance Score must be between 0 and 2")
  private Integer appearance;

  @Range(min = 0, max = 4, message = "The Pulse Score must be between 0 and 2")
  private Integer pulse;

  @Range(min = 0, max = 4, message = "The Grimace Score must be between 0 and 2")
  private Integer grimace;

  @Range(min = 0, max = 4, message = "The Activity Score must be between 0 and 2")
  private Integer activity;

  @Range(min = 0, max = 4, message = "The Respiration Score must be between 0 and 2")
  private Integer respiration;

  @OneToOne
  private NeurologicalObservationChart chart;

  @Column(updatable = false)
  private Instant createdAt;
  @Column(insertable  = false)
  private Instant updatedAt;

  public ChildEntity() {
  }

  public ChildEntity(
          Long id,
          Integer appearance,
          Integer pulse,
          Integer grimace,
          Integer activity,
          Integer respiration,
          NeurologicalObservationChart chart
          ) {
    this.id = id;
    this.appearance = appearance;
    this.pulse = pulse;
    this.grimace = grimace;
    this.activity = activity;
    this.respiration = respiration;
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
