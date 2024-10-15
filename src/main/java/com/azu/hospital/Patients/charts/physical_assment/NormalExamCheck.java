package com.azu.hospital.Patients.charts.physical_assment;

import com.azu.hospital.Patients.charts.physical_assessment_enm.*;
import lombok.Data;

@Data
public class NormalExamCheck {
  private NormalExamCheckGeneral general;
  private NormalExamCheckEnt ent;
  private NormalExamCheckNeck neck;
  private NormalExamCheckCV cv;
  private NormalExamCheckLungs lungs;
  private NormalExamCheckAbd abd;
  private NormalExamCheckExt ext;
  private NormalExamCheckNeuro neuro;

  public NormalExamCheck() {
  }

  public NormalExamCheck(
          NormalExamCheckGeneral general,
          NormalExamCheckEnt ent,
          NormalExamCheckNeck neck,
          NormalExamCheckCV cv,
          NormalExamCheckLungs lungs,
          NormalExamCheckAbd abd,
          NormalExamCheckExt ext,
          NormalExamCheckNeuro neuro
  ) {
    this.general = general;
    this.ent = ent;
    this.neck = neck;
    this.cv = cv;
    this.lungs = lungs;
    this.abd = abd;
    this.ext = ext;
    this.neuro = neuro;
  }
}
