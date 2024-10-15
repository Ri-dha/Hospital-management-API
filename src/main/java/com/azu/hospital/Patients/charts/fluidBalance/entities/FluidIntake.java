package com.azu.hospital.Patients.charts.fluidBalance.entities;

import com.azu.hospital.Patients.charts.fluidBalance.fluid_balance_classes.FieldFluidRequest;
import com.azu.hospital.Patients.charts.fluidBalance.fluid_balance_classes.FluidByIntravenous;
import com.azu.hospital.Patients.charts.fluidBalance.fluid_balance_classes.Oral;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Table(name = "fluid_intake")
@Entity
@Getter
@Setter
public class FluidIntake {
  @Id
  @SequenceGenerator(
          name = "fluid_intake_sequence",
          sequenceName = "fluid_intake_sequence"
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "fluid_intake_sequence"
  )
  private Long fluidIntakeId;

  @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Time format should be like HH:MM")
  private String time;

  @Embedded
  private Oral oral;

  @Embedded
  @AttributeOverrides({
          @AttributeOverride(name="value", column=@Column(name="nasogastric_value")),
          @AttributeOverride(name="total", column=@Column(name="nasogastric_total"))
  })
  private FieldFluidRequest nasogastricFeed;

  @Embedded
  private FluidByIntravenous fluidByIntravenous;

  @Embedded
  @AttributeOverrides({
          @AttributeOverride(name="value", column=@Column(name="hourly_value")),
          @AttributeOverride(name="total", column=@Column(name="hourly_total"))
  })
  private FieldFluidRequest hourlyTotal;

  @Embedded
  @AttributeOverrides({
          @AttributeOverride(name="value", column=@Column(name="running_value")),
          @AttributeOverride(name="total", column=@Column(name="running_total"))
  })
  private FieldFluidRequest runningInputTotal;

  public FluidIntake() {
  }

  public FluidIntake(
          Long fluidIntakeId,
          String time,
          Oral oral,
          FluidByIntravenous fluidByIntravenous,
          FieldFluidRequest hourlyTotal,
          FieldFluidRequest nasogastricFeed,
          FieldFluidRequest runningInputTotal
  ) {
    this.fluidIntakeId = fluidIntakeId;
    this.time = time;
    this.oral = oral;
    this.fluidByIntravenous = fluidByIntravenous;
    this.hourlyTotal = hourlyTotal;
    this.runningInputTotal = runningInputTotal;
    this.nasogastricFeed = nasogastricFeed;
  }

  public FluidIntake(
          String time,
          Oral oral,
          FluidByIntravenous fluidByIntravenous,
          FieldFluidRequest hourlyTotal,
          FieldFluidRequest runningInputTotal,
          FieldFluidRequest nasogastricFeed
  ) {
    this.time = time;
    this.oral = oral;
    this.fluidByIntravenous = fluidByIntravenous;
    this.hourlyTotal = hourlyTotal;
    this.nasogastricFeed = nasogastricFeed;
    this.runningInputTotal = runningInputTotal;
  }
}
