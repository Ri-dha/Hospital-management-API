package com.azu.hospital.Patients.charts.physical_assment;

import com.azu.hospital.Patients.charts.physical_assessment_enm.BodyContactEnum;
import com.azu.hospital.Patients.charts.physical_assessment_enm.Positions;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.Data;

import java.util.Map;

@Data
public class PatientPositions {

  private Positions positions;

  @ElementCollection
  private Map<BodyContactEnum, Boolean> bodyContact;

  private Boolean intact = false;

  private Boolean patientPositionsOther = false;
  private String patientPositionsOtherNote;

  private Boolean isNPO = false;
  private Boolean isConsentSigned = false;
  private Boolean isVerification = false;

  public PatientPositions() {
  }

  public PatientPositions(
          Positions positions,
          Map<BodyContactEnum, Boolean> bodyContact,
          Boolean intact,
          Boolean patientPositionsOther,
          String patientPositionsOtherNote,
          Boolean isNPO,
          Boolean isConsentSigned,
          Boolean isVerification
  ) {
    this.positions = positions;
    this.bodyContact = bodyContact;
    this.intact = intact;
    this.patientPositionsOther = patientPositionsOther;
    this.patientPositionsOtherNote = patientPositionsOtherNote;
    this.isNPO = isNPO;
    this.isConsentSigned = isConsentSigned;
    this.isVerification = isVerification;
  }
}
