package com.azu.hospital.Patients.charts.physical_assessment_enm;

public enum MedicalConditionEnum {
  CKD_STAGE("CKD Stage _____ /Dialysis"),
  TIA_CVA_HEMIPLEGIA("TIA/CVA/hemiplegia/hemiparesis/residual deficit ______"),
  DVT_PE("DVT/PE"),
  ANEMIA("Anemia"),
  ACTIVE_INFECTION("Active infection/sepsis"),
  ASTHMA_COPD("Asthma/COPD"),
  CHRONIC_RESP_FAILURE_ON_HOME_O2("Chronic Resp Failure on Home O2"),
  CANCER("Cancer"),
  CHRONIC_STEROIDS("Chronic Steroids"),
  CIRRHOSIS("Cirrhosis"),
  COAGULOPATHY("Coagulopathy/on anticoagulation"),
  DIABETES("Diabetes ( Insulin Yes / No )"),
  HIV_AIDS("HIV/AIDS"),
  CHRONIC_HEPATITIS("Chronic Hepatitis B/C /treated"),
  OBESITY_HYPOVENTILATION_SYNDROME("Obesity w/hypoventilation syndrome"),
  OSA_CPAP_ADHERENCE("OSA (if yes, is the patient adherent to CPAP?)"),
  STOPBANG("STOPBANG");

  private final String description;

  MedicalConditionEnum(String description) {
    this.description = description;
  }



  public String getDescription() {
    return description;
  }


}

