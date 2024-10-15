package com.azu.hospital.accounting.patientBill.dto;

import com.azu.hospital.accounting.patientBill.entity.PatientBill;
import org.springframework.stereotype.Service;

@Service
public class PatientBillDtoMapper {

    public PatientBillDto toDto(PatientBill patientBill){
        return new PatientBillDto(
                patientBill.getId(),
                patientBill.getPatient().getId(),
                patientBill.getPatient().getPatientData().getFullName(),
                patientBill.getNote() == null ? null : patientBill.getNote(),
                patientBill.getQuantity(),
                patientBill.getPrice(),
                patientBill.getFullPrice(),
                patientBill.getDiscount() == null ? null : patientBill.getDiscount(),
                patientBill.getType(),
                patientBill.getCreatedAt(),
                patientBill.getUpdatedAt() == null ?  null : patientBill.getUpdatedAt()
        );
    }
}
