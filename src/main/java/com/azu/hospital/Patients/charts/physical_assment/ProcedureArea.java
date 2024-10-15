package com.azu.hospital.Patients.charts.physical_assment;

import com.azu.hospital.Patients.charts.physical_assessment_enm.ProcedureAreaEnum;
import lombok.Data;

@Data
public class ProcedureArea {
  private ProcedureAreaEnum area;
  private String tubes;
  private String drains;
  private String procedureAreaOther;

  public ProcedureArea() {
  }

  public ProcedureArea(ProcedureAreaEnum area, String tubes, String drains, String procedureAreaOther) {
    this.area = area;
    this.tubes = tubes;
    this.drains = drains;
    this.procedureAreaOther = procedureAreaOther;
  }
}
