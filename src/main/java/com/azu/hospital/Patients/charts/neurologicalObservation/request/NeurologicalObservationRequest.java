package com.azu.hospital.Patients.charts.neurologicalObservation.request;

import com.azu.hospital.Patients.charts.neurologicalObservation.entities.AdultEntity;
import com.azu.hospital.Patients.charts.neurologicalObservation.entities.ChildEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NeurologicalObservationRequest {
  private Long id;

  private LocalDate date;

  private String candidateName;

  private AdultEntity adult;

  private ChildEntity child;

  private String note;

  public NeurologicalObservationRequest() {
  }

  public NeurologicalObservationRequest(
          Long id,
          LocalDate date,
          String candidateName,
          AdultEntity adult,
          ChildEntity child,
          String note
  ) {
    this.id = id;
    this.date = date;
    this.candidateName = candidateName;
    this.adult = adult;
    this.child = child;
    this.note = note;
  }
}
