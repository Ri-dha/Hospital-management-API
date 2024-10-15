package com.azu.hospital.Patients.charts.physical_assment;

import com.azu.hospital.Patients.charts.physical_assessment_enm.Limitation;
import com.azu.hospital.Patients.charts.physical_assessment_enm.LimitationList;
import jakarta.persistence.ElementCollection;
import lombok.Data;

import java.util.Map;

@Data
public class PatientLimitation {

  private Limitation limitation;

  @ElementCollection
  private Map<LimitationList, Boolean> list;

  public PatientLimitation() {
  }

  public PatientLimitation(Limitation limitation, Map<LimitationList, Boolean> list) {
    this.limitation = limitation;
    this.list = list;
  }
}
