package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.dto;

import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dto.DrugRequestHandlerDto;
import com.azu.hospital.utils.enums.Gender;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class DrugRequestHandlerListDto {
    private Long drugRequestHandlerListId;
    private Long patientId;
    private String patientName;
    private String doctorName;
    private Long doctorId;
    private Gender patientGender;
    private Float patientHeight;
    private Float patientWeight;
    private String admissionDate;
    private String bedNumber;
    private String wardName ;
    private String patientAge ;
    private String allergy;
    private String diagnosis;
    private Long signatures; // TODO: 04/01/2024 this value should be return from token
    private List<DrugRequestHandlerDto> drugRequestHandlerDto ;
    private Instant createdAt;
    private Instant updatedAt;
    private int acceptedCount ;
    private int rejectedCount ;

    private boolean isCompleted;

    public DrugRequestHandlerListDto() {
    }

    public DrugRequestHandlerListDto(Long drugRequestHandlerListId, Long patientId,
                                     String patientName, String doctorName, Long doctorId,
                                     Gender patientGender,
                                     Float patientHeight, Float patientWeight, String admissionDate,
                                     String bedNumber, String wardName, String patientAge, String allergy,
                                     String diagnosis, Long signatures,
                                     List<DrugRequestHandlerDto> drugRequestHandlerDto,
                                     Instant createdAt,
                                     Instant updatedAt,int acceptedCount,int rejectedCount,
                                     boolean isCompleted
    ) {
        this.drugRequestHandlerListId = drugRequestHandlerListId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.doctorId = doctorId;
        this.patientGender = patientGender;
        this.patientHeight = patientHeight;
        this.patientWeight = patientWeight;
        this.admissionDate = admissionDate;
        this.bedNumber = bedNumber;
        this.wardName = wardName;
        this.patientAge = patientAge;
        this.allergy = allergy;
        this.diagnosis = diagnosis;
        this.signatures = signatures;
        this.drugRequestHandlerDto = drugRequestHandlerDto;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.acceptedCount = acceptedCount;
        this.rejectedCount = rejectedCount;
        this.isCompleted = isCompleted;
    }
}



