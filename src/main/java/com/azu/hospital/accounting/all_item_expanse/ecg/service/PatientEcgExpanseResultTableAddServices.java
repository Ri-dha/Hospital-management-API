package com.azu.hospital.accounting.all_item_expanse.ecg.service;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.accounting.all_item_expanse.ecg.dao.PatientEcgExpanseResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.ecg.entity.PatientEcgExpanseResultTable;
import com.azu.hospital.accounting.patient_wallet.services.IPatientWalletService;
import com.azu.hospital.ecg.internal.dao.EcgDao;
import com.azu.hospital.ecg.internal.entity.Ecg;
import com.azu.hospital.ecg.internal.services.EcgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("PatientEcgExpanseResultTableAddServicesImp")
public class PatientEcgExpanseResultTableAddServices implements IPatientEcgExpanseResultTableAddServices{

    private final PatientEcgExpanseResultTableDao dao;
    private final EcgDao ecgDao;
    private final PatientDao patientDao;

    private final IPatientWalletService iPatientWalletService;
    @Autowired
    public PatientEcgExpanseResultTableAddServices(
            @Qualifier("PatientEcgExpanseResultTableJpa") PatientEcgExpanseResultTableDao dao,
            @Qualifier("ecgJpa") EcgDao ecgDao, PatientDao patientDao, IPatientWalletService iPatientWalletService) {
        this.dao = dao;
        this.ecgDao = ecgDao;
        this.patientDao = patientDao;
        this.iPatientWalletService = iPatientWalletService;
    }

    @Override
    public void addPatientEcgExpanseResultTable(Long patientId , Ecg ecg){
        Patient patient = patientDao.getPatientById(patientId).orElseThrow(
                () -> new RuntimeException("Patient Doesn't Exists")
        );
//
//        PatientEcgExpanseResultTable ecgExpanseResultTable = new PatientEcgExpanseResultTable();
//        ecgExpanseResultTable.setPatient(patient);
//        ecgExpanseResultTable.setEcgId(ecg.getId());
//        ecgExpanseResultTable.setEcgName("Ecg");
//        ecgExpanseResultTable.setEcgCount(1);
//        ecgExpanseResultTable.setEcgPrice(ecg.getEcgBill().getPrice());
//        ecgExpanseResultTable.setTotalEcgPrice(ecg.getEcgBill().getPrice());
//
//        dao.createPatientEcgExpanseResultTable(ecgExpanseResultTable);


    }

}
