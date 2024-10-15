package com.azu.hospital.accounting.hospitalaccounting;

import com.azu.hospital.accounting.hospitalaccounting.services.HospitalAccountingGetServices;
import com.azu.hospital.accounting.hospitalaccounting.services.HospitalAccountingMathServices;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/hospital-accounting")
@CrossOrigin
public class HospitalAccountingController {

    private final HospitalAccountingGetServices getServices;
    private final HospitalAccountingMathServices mathServices;

    public HospitalAccountingController(HospitalAccountingGetServices getServices, HospitalAccountingMathServices mathServices) {
        this.getServices = getServices;
        this.mathServices = mathServices;
    }


}
