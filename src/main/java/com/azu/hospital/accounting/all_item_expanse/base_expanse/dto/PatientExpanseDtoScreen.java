package com.azu.hospital.accounting.all_item_expanse.base_expanse.dto;

import com.azu.hospital.Patients.Patient.dto.PatientDto;
import com.azu.hospital.ph.Sales.dto.SoldBillDto;
import lombok.Data;

import java.util.List;

@Data
public class PatientExpanseDtoScreen {
    private PatientDto patientDto;
    private List<ExpanseDtoScreen> expanseDtoScreen;
    private List<SoldBillDto> soldBillDtoList;

    public PatientExpanseDtoScreen() {
    }

    public PatientExpanseDtoScreen(PatientDto patientDto, List<ExpanseDtoScreen> expanseDtoScreen, List<SoldBillDto> soldBillDtoList) {
        this.patientDto = patientDto;
        this.expanseDtoScreen = expanseDtoScreen;
        this.soldBillDtoList = soldBillDtoList;
    }
}
