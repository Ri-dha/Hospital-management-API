package com.azu.hospital.accounting.add_priceses.request;

import com.azu.hospital.Patients.Patient.dto.PatientDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillScreenDto <T>{

    private PatientDto patient ;

    private List<BillScreenDataDto<T>> data;

}
