package com.azu.hospital.Patients.PrematureBaby.dto;


import com.azu.hospital.Patients.PrematureBaby.entity.PrematureBaby;
import com.azu.hospital.utils.enums.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrematureBabyDto {

    private Long id;

    private String name;

    private String headCircumference;

    private Float weight;

    private Float height;

    private Long motherId;

    private String motherName;

    private Long doctorId;
    private String doctorName;

    private String mobile;

    private String relativeMobile;

    private String birthDate;
    private String age;

    private Long wardId;
    private String wardName;
    private Long bedId;
    private String bedName;

    private Long createdBy;
    private Long LastModifiedBy;

    private Boolean isDischarged;

    private Gender gender;




    public PrematureBabyDto() {
    }

    public PrematureBabyDto(
            Long id,
            String name,
            String headCircumference,
            Float weight,
            Float height,
            Long motherId,
            String motherName,
            String mobile,
            String relativeMobile,
            String birthDate,
            String age,
            Long wardId,
            String wardName,
            Long bedId,
            String bedName,
            Long createdBy,
            Long LastModifiedBy,
            Boolean isDischarged,
            Gender gender,
            Long doctorId,
            String doctorName
    ) {
        this.id = id;
        this.name = name;
        this.headCircumference = headCircumference;
        this.weight = weight;
        this.height = height;
        this.motherId = motherId;
        this.motherName = motherName;
        this.mobile = mobile;
        this.relativeMobile = relativeMobile;
        this.birthDate = birthDate;
        this.age = age;
        this.wardId = wardId;
        this.wardName = wardName;
        this.bedId = bedId;
        this.bedName = bedName;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
        this.isDischarged = isDischarged;
        this.gender =gender;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
    }


    public PrematureBabyDto(PrematureBaby prematureBaby) {
        this.id = prematureBaby.getId();
        this.name = prematureBaby.getName();
        this.headCircumference = prematureBaby.getHeadCircumference();
        this.weight = prematureBaby.getWeight();
        this.height = prematureBaby.getHeight();
        this.motherId = prematureBaby.getPatient().getId();
        this.motherName = prematureBaby.getPatient().getPatientData().getFullName();
        this.mobile = prematureBaby.getPatient().getPatientContact().getMobile();
        this.relativeMobile = prematureBaby.getPatient().getPatientContact().getRelativeMobile();
        this.birthDate = prematureBaby.getPrematureBabyDate().getBirthDate();
        this.age = prematureBaby.getPrematureBabyDate().getAge();
        this.wardId = prematureBaby.getWard().getWardId();
        this.wardName = prematureBaby.getWard().getName();
        this.bedId = prematureBaby.getBed().getId();
        this.bedName = prematureBaby.getBed().getBedNumber();
        this.createdBy = prematureBaby.getCreatedBy();
        this.LastModifiedBy = prematureBaby.getLastModifiedBy();
        this.isDischarged = prematureBaby.getIsDischarged();
        this.gender = prematureBaby.getGender();
        this.doctorId = prematureBaby.getDoctor().getId();
        this.doctorName = prematureBaby.getDoctor().getUsernameSpecial();

    }
}
