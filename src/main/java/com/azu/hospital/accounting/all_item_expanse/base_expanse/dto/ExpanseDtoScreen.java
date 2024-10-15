package com.azu.hospital.accounting.all_item_expanse.base_expanse.dto;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dto.PatientDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class ExpanseDtoScreen {

    private String name;
    List<ExpanseDto> expanseDto;

    public ExpanseDtoScreen(String name, List<ExpanseDto> expanseDto) {
        this.name = name;
        this.expanseDto = expanseDto;
    }

    public ExpanseDtoScreen() {
    }
}
