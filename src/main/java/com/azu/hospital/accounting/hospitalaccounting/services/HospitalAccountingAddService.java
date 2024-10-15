package com.azu.hospital.accounting.hospitalaccounting.services;

import com.azu.hospital.accounting.hospitalaccounting.dao.HospitalAccountingDao;
import com.azu.hospital.accounting.hospitalaccounting.entity.HospitalAccounting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class HospitalAccountingAddService {

    private final HospitalAccountingDao hospitalAccountingDao;



    @Autowired
    public HospitalAccountingAddService(
            HospitalAccountingDao hospitalAccountingDao) {
        this.hospitalAccountingDao = hospitalAccountingDao;

    }


    public void addNewHospitalAccounting(
            String operationName,
            long operationId,
            long patientId,
            double hospitalPercentage,
            double operationCost,
            String patientName
    ){



        HospitalAccounting hospitalAccounting = new HospitalAccounting();
        hospitalAccounting.setOperationId(operationId);
        hospitalAccounting.setOperationName(operationName);
        hospitalAccounting.setHospitalIncome(hospitalPercentage);
        hospitalAccounting.setPatientId(patientId);
        hospitalAccounting.setPatientName(patientName);
        hospitalAccounting.setOperationCost(operationCost);
        hospitalAccounting.setArchived(false);
        hospitalAccounting.setDate(Date.from(Instant.now()));
        hospitalAccountingDao.insertHospitalIncome(hospitalAccounting);


    }
}
