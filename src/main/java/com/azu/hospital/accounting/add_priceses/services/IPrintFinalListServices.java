package com.azu.hospital.accounting.add_priceses.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.accounting.add_priceses.request.BillScreenDto;
import com.azu.hospital.accounting.patient_wallet.entity.PatientWallet;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;

import java.util.List;

public interface IPrintFinalListServices {
    void payButtonProcess(Patient patient , PatientWallet patientWallet);
    List<BillsDtoSum<String>> getFinalPatientBills(Long patientId);

    List<BillsDtoSum<RadiologyTypeEnum>> getFinalPatientBillsRadiology(Long patientId);

    List<BillsDtoSum<UltrasoundTypeEnum>> getFinalPatientBillsUltrasound(Long patientId);

    List<BillsDtoSum<String>> getFinalPatientBillsLab(Long patientId);

    BillScreenDto getFinalPatientBill(Long patientId);

    List<BillsDtoSum<String>> getFinalPatientBillsOtherConsumables(Long patientId);


}
