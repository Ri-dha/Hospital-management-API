package com.azu.hospital.accounting.all_item_expanse.base_expanse.dto;


import com.azu.hospital.Patients.Patient.dto.PatientDto;
import com.azu.hospital.Patients.entryTable.dto.EntryTableDateDto;
import com.azu.hospital.ph.Sales.dto.SoldBillDto;
import com.azu.hospital.ph.Sales.entity.SoldBill;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FinalPatientExpensesDtoScreen {

    private PatientDto patientDto;
    private List<ExpanseDtoScreen> expanseDtoScreen;
    private List<SoldBillDto> soldBillDtoList;
    private EntryTableDateDto entryTableDto;


    public FinalPatientExpensesDtoScreen() {
    }


    public FinalPatientExpensesDtoScreen(PatientDto patientDto, List<ExpanseDtoScreen> expanseDtoScreen, List<SoldBillDto> soldBillDtoList, EntryTableDateDto entryTableDto) {
        this.patientDto = patientDto;
        this.expanseDtoScreen = expanseDtoScreen;
        this.soldBillDtoList = soldBillDtoList;
        this.entryTableDto = entryTableDto;
    }
}
