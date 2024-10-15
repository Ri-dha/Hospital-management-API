package com.azu.hospital.Patients.Patient.request;


import com.azu.hospital.Patients.PatentEnum.CertificationEnum;
import com.azu.hospital.utils.enums.Gender;
import com.azu.hospital.utils.enums.patient.JopTypeEnum;
import com.azu.hospital.utils.enums.patient.LiveEnum;
import com.azu.hospital.utils.enums.patient.SocialStateEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class UpdatePatientRequest {


    @NotNull(message = "Patient must not be null")
    private Long patientId;

    private Long doctorId;

//    @NotNull(message = "permanentDoctor id required")
//    private Long permanentDoctor;

    private List<MultipartFile> ids;

    private List<MultipartFile> policeStatus;

    private List<MultipartFile> files;

    private MultipartFile image;

    private JobInfo jobInfo;


    private PatientAddress patientAddress;


    private PatientContact patientContact;


    private PatientData patientData;


    private PatientDate patientDate;


    private PatientMedicalInfo patientMedicalInfo;

    private List<MultipartFile> tests;


    public UpdatePatientRequest() {
    }



    @JsonCreator
    public UpdatePatientRequest(
            @JsonProperty("doctorId") Long doctorId,
            @JsonProperty("ids") List<MultipartFile> ids,
            @JsonProperty("files") List<MultipartFile> files,
            @JsonProperty("files") List<MultipartFile> tests,
            @JsonProperty("image") MultipartFile image,
            @JsonProperty("job") String job,
            @JsonProperty("jobType") JopTypeEnum jobType,
            @JsonProperty("birthAddress") String birthAddress,
            @JsonProperty("address") String address,
            @JsonProperty("email") String email,
            @JsonProperty("mobile") String mobile,
            @JsonProperty("relativeMobile") String relativeMobile,
            @JsonProperty("fullName") String fullName,
            @JsonProperty("motherName") String motherName,
            @JsonProperty("gender") Gender gender,
            @JsonProperty("weight") Float weight,
            @JsonProperty("height") Float height,
            @JsonProperty("certification") CertificationEnum certification,
            @JsonProperty("liveEnum") LiveEnum liveEnum,
            @JsonProperty("socialState") SocialStateEnum socialState ,
            @JsonProperty("birthDate") String birthDate,
            @JsonProperty("admissionDate") String admissionDate,
            @JsonProperty("age") Integer age,
            @JsonProperty("apparentImpairments")String apparentImpairments,
            @JsonProperty("timeOfEntries") Integer timeOfEntries,
            @JsonProperty("referredFrom") String referredFrom,
            @JsonProperty("transformations") String transformations
    ) {
        this.doctorId = doctorId;

        this.ids = ids;

        this.files = files;

        this.tests = tests;

        this.image = image;

        this.jobInfo = new JobInfo(
                job ,
                jobType
        );

        this.patientAddress = new PatientAddress(
                birthAddress ,
                address
        );

        this.patientContact = new PatientContact(
                email ,
                mobile,
                relativeMobile
        );

        this.patientData = new PatientData(
                fullName,
                motherName,
                gender,
                weight,
                height,
                certification,
                liveEnum,
                socialState

        );

        this.patientMedicalInfo = new PatientMedicalInfo(
                apparentImpairments,
                timeOfEntries,
                referredFrom,
                transformations
        );

        this.patientDate = new PatientDate(
                birthDate ,
                admissionDate,
                age
        );


    }
}
