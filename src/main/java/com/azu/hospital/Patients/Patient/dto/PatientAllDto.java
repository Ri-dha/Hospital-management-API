package com.azu.hospital.Patients.Patient.dto;

import com.azu.hospital.Patients.PatentEnum.CertificationEnum;
import com.azu.hospital.utils.enums.Gender;
import com.azu.hospital.utils.enums.patient.*;
import com.azu.hospital.utils.files.File;
import com.azu.hospital.utils.image.Image;

import java.time.Instant;
import java.util.List;

public record PatientAllDto (

        Long id,
        String job,
        JopTypeEnum jobType,
        String birthAddress,
        String address,
        String email,
        String mobile,
        String relativeMobile,
        String fullName,
        String motherName,
        Gender gender,
        Float weight,
        Float height,
        CertificationEnum certification,
        LiveEnum liveEnum,
        SocialStateEnum socialState,
        String operation,
        String birthDate,
        String admissionDate,
        String age,
        String apparentImpairments,
        Integer timeOfEntries,
        String referredFrom,
        String transformations,
        Long doctorId,
        String doctor,
        String permanentDoctor,
        List<Image> images,
        List<File> files,
        String image,
        String wardName,
        String bedNumber,
        Instant createdAt,
        String qrCode,
        Boolean hasMedicalHistory,
        PatientTriageEnum triage,
        BillState billState,
        String dischargedDate,
        Long entryNumber,
        String floorName,
        List<File> tests

){
}
