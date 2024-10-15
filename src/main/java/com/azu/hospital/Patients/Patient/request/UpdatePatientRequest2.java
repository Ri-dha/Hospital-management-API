package com.azu.hospital.Patients.Patient.request;

import com.azu.hospital.Patients.PatentEnum.CertificationEnum;
import com.azu.hospital.Validator.DateValidator.DatePattern;
import com.azu.hospital.utils.enums.Gender;
import com.azu.hospital.utils.enums.patient.JopTypeEnum;
import com.azu.hospital.utils.enums.patient.LiveEnum;
import com.azu.hospital.utils.enums.patient.SocialStateEnum;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Getter
@Setter
public class UpdatePatientRequest2 {

    @NotNull(message = "Patient must not be null")
    private Long patientId;

    private Long doctorId;

//    @NotNull(message = "permanentDoctor id required")
//    private Long permanentDoctor;

    private List<MultipartFile> ids;

    private List<MultipartFile> policeStatus;

    private List<MultipartFile> files;

    private MultipartFile image;

    private String job;

    @Enumerated
    private JopTypeEnum jobType;

    private String birthAddress;

    private String address;

    @Nullable
    @Email(
            regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
            message = "Email Not Valid"
    )
    private String email;

    @Size(min = 10 , max = 10 , message = "Mobile must contains 10 number")
    @Column(unique = true)
    private String mobile;


    @Nullable
    @Size(min = 10 , max = 10 , message = "Mobile must contains 10 number")
    private String relativeMobile;

    @Column(unique = true)
    private String fullName;

    private String motherName;

    @Enumerated
    private Gender gender;

    @NumberFormat
    private Float weight;

    @NumberFormat
    private Float height;

    @Enumerated
    private CertificationEnum certification;

    @Enumerated
    private LiveEnum liveEnum;

    @Enumerated
    private SocialStateEnum socialState;

    private String operation;


    @DatePattern(message = "Birth date has Invalid date format. Expected format: yyyy-MM-dd")
    private String birthDate;

    @DatePattern(message = "Admission date has Invalid date format. Expected format: yyyy-MM-dd")
    private String admissionDate;

//    private Integer age;

    @Nullable
    private String apparentImpairments;

    @NumberFormat
    private Integer timeOfEntries;

    @Nullable
    private String referredFrom;

    @Nullable
    private String transformations;

    private List<MultipartFile> tests;


    public UpdatePatientRequest2() {
    }

    public UpdatePatientRequest2(Long patientId, Long doctorId, List<MultipartFile> ids,
                                 List<MultipartFile> policeStatus, List<MultipartFile> files,
                                 MultipartFile image, String job, JopTypeEnum jobType,
                                 String birthAddress, String address, @Nullable String email,
                                 String mobile, @Nullable String relativeMobile, String fullName,
                                 String motherName, Gender gender, Float weight, Float height,
                                 CertificationEnum certification, LiveEnum liveEnum, SocialStateEnum socialState,
                                 String operation, String birthDate, String admissionDate,
//                                 Integer age,
                                 @Nullable String apparentImpairments, Integer timeOfEntries,
                                 @Nullable String referredFrom, @Nullable String transformations, List<MultipartFile> tests) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.ids = ids;
        this.policeStatus = policeStatus;
        this.files = files;
        this.image = image;
        this.job = job;
        this.jobType = jobType;
        this.birthAddress = birthAddress;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
        this.relativeMobile = relativeMobile;
        this.fullName = fullName;
        this.motherName = motherName;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.certification = certification;
        this.liveEnum = liveEnum;
        this.socialState = socialState;
        this.operation = operation;
        this.birthDate = birthDate;
        this.admissionDate = admissionDate;
//        this.age = age;
        this.apparentImpairments = apparentImpairments;
        this.timeOfEntries = timeOfEntries;
        this.referredFrom = referredFrom;
        this.transformations = transformations;
        this.tests = tests;
    }
}
