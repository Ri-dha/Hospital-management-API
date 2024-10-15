package com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList.dto;

import com.azu.hospital.newPh.MedicinsRequests.dto.MedicinesRequestsDto;
import com.azu.hospital.utils.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class MedicinesRequestsListDto {
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
    private String wardName;
    private String patientAge;
    private String allergy;
    private String diagnosis;
    private Instant createdAt;
    private Instant updatedAt;
    private List<MedicinesRequestsDto> medicinesRequestsDto;


    public MedicinesRequestsListDto() {
    }

    public MedicinesRequestsListDto(Long drugRequestHandlerListId, Long patientId,
                                    String patientName, String doctorName,
                                    Long doctorId, Gender patientGender,
                                    Float patientHeight, Float patientWeight,
                                    String admissionDate, String bedNumber,
                                    String wardName, String patientAge,
                                    String allergy, String diagnosis,
                                    Instant createdAt, Instant updatedAt,
                                    List<MedicinesRequestsDto> medicinesRequestsDto) {
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
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.medicinesRequestsDto = medicinesRequestsDto;
    }
}
