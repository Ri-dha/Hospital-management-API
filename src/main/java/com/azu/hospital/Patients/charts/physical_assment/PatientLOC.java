package com.azu.hospital.Patients.charts.physical_assment;

import com.azu.hospital.Patients.charts.physical_assessment_enm.LocTableSystem;
import com.azu.hospital.Patients.charts.physical_assessment_enm.PatientLOCEnum;
import jakarta.persistence.ElementCollection;
import lombok.Data;

import java.util.Map;

@Data
public class PatientLOC {

  private PatientLOCEnum levelOfConsciousness;

  @ElementCollection
  private Map<LocTableSystem, Boolean> tableSystem;

  public PatientLOC() {
  }

  public PatientLOC(PatientLOCEnum levelOfConsciousness, Map<LocTableSystem, Boolean> tableSystem) {
    this.levelOfConsciousness = levelOfConsciousness;
    this.tableSystem = tableSystem;
  }
}
