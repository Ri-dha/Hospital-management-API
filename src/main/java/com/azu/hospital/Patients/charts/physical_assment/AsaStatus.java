package com.azu.hospital.Patients.charts.physical_assment;

import com.azu.hospital.Patients.charts.physical_assessment_enm.AsaStatusEnum;
import lombok.Data;

@Data
public class AsaStatus {
  private AsaStatusEnum status;

  private String cxr;

  private String asaStatusOther;

  public AsaStatus() {
  }

  public AsaStatus(AsaStatusEnum status, String cxr, String asaStatusOther) {
    this.status = status;
    this.cxr = cxr;
    this.asaStatusOther = asaStatusOther;
  }
}
