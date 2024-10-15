package com.azu.hospital.Patients.patientDrugs.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDrugRequestUpdate {
        private Long drugId;
        private String drug;
        private String dose;
        private String time;
        private String roa;
        private Integer count;
        private String note;


    public PatientDrugRequestUpdate() {
    }


    public PatientDrugRequestUpdate(Long drugId, String drug, String dose, String time, String roa, Integer count, String note) {
        this.drugId = drugId;
        this.drug = drug;
        this.dose = dose;
        this.time = time;
        this.roa = roa;
        this.count = count;
        this.note = note;
    }
}
