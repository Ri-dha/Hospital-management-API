package com.azu.hospital.Patients.charts.fluidBalance.entities;

import com.azu.hospital.Patients.charts.fluidBalance.fluid_balance_classes.FieldFluidRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Table(name = "fluid_output")
@Entity
@Getter
@Setter
public class FluidOutput {
  @Id
  @SequenceGenerator(
          name = "fluid_output_sequence",
          sequenceName = "fluid_output_sequence"
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "fluid_output_sequence"
  )
  private Long fluidOutputId;

  @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Time format should be like HH:MM")
  private String time;

  @Embedded
  @AttributeOverrides({
          @AttributeOverride(name="value", column=@Column(name="urine_value")),
          @AttributeOverride(name="total", column=@Column(name="urine_total"))
  })
  private FieldFluidRequest urine;

  @Embedded
  @AttributeOverrides({
          @AttributeOverride(name="value", column=@Column(name="bowel_or_stoma_value")),
          @AttributeOverride(name="total", column=@Column(name="bowel_or_stoma_total"))
  })
  private FieldFluidRequest bowelOrStoma;

  @Embedded
  @AttributeOverrides({
          @AttributeOverride(name="value", column=@Column(name="first_drain_value")),
          @AttributeOverride(name="total", column=@Column(name="first_drain_total"))
  })
  private FieldFluidRequest firstDrain;

  @Embedded
  @AttributeOverrides({
          @AttributeOverride(name="value", column=@Column(name="second_drain_value")),
          @AttributeOverride(name="total", column=@Column(name="second_drain_total"))
  })
  private FieldFluidRequest secondDrain;

  @Embedded
  @AttributeOverrides({
          @AttributeOverride(name="value", column=@Column(name="third_drain_value")),
          @AttributeOverride(name="total", column=@Column(name="third_drain_total"))
  })
  private FieldFluidRequest thirdDrain;

  @Embedded
  @AttributeOverrides({
          @AttributeOverride(name="value", column=@Column(name="four_drain_value")),
          @AttributeOverride(name="total", column=@Column(name="four_drain_total"))
  })
  private FieldFluidRequest fourDrain;

  @Embedded
  @AttributeOverrides({
          @AttributeOverride(name="value", column=@Column(name="running_output_value")),
          @AttributeOverride(name="total", column=@Column(name="running_output_total"))
  })
  private FieldFluidRequest runningOutputTotal;

  @Column(updatable = false)
  private Instant createdAt;

  private Instant updatedAt;

  public FluidOutput() {
  }

  public FluidOutput(
          Long fluidOutputId,
          String time,
          FieldFluidRequest urine,
          FieldFluidRequest bowelOrStoma,
          FieldFluidRequest firstDrain,
          FieldFluidRequest secondDrain,
          FieldFluidRequest thirdDrain,
          FieldFluidRequest fourDrain,
          FieldFluidRequest runningOutputTotal
  ) {
    this.fluidOutputId = fluidOutputId;
    this.time = time;
    this.urine = urine;
    this.bowelOrStoma = bowelOrStoma;
    this.firstDrain = firstDrain;
    this.secondDrain = secondDrain;
    this.thirdDrain = thirdDrain;
    this.fourDrain = fourDrain;
    this.runningOutputTotal = runningOutputTotal;
  }

  public FluidOutput(
          String time,
          FieldFluidRequest urine,
          FieldFluidRequest bowelOrStoma,
          FieldFluidRequest firstDrain,
          FieldFluidRequest secondDrain,
          FieldFluidRequest thirdDrain,
          FieldFluidRequest fourDrain,
          FieldFluidRequest runningOutputTotal
  ) {
    this.time = time;
    this.urine = urine;
    this.bowelOrStoma = bowelOrStoma;
    this.firstDrain = firstDrain;
    this.secondDrain = secondDrain;
    this.thirdDrain = thirdDrain;
    this.fourDrain = fourDrain;
    this.runningOutputTotal = runningOutputTotal;
  }

  @PrePersist
  private void onCreate(){
    this.createdAt = Instant.now();
  }
  @PreUpdate
  private void onUpdate(){
    this.updatedAt = Instant.now();
  }

}
