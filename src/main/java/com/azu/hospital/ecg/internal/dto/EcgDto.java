package com.azu.hospital.ecg.internal.dto;

import com.azu.hospital.utils.enums.Gender;
import com.azu.hospital.utils.enums.ecg.EcgStates;
import com.azu.hospital.utils.files.File;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class EcgDto {

    private Long id;

    private EcgStates state;

    private List<File> files;
    private String note;
    private String noteFromConsultant;
    private Long uploaderId;

    private String uploaderName;

    private String wardName;

    private String bedNumber;

    private Long patientId;

    private String patientName;


    private Instant date;

    private String dx;

    private String allergy;

    private Gender gender;

    private String age;

    private Float weight;

    private Float height;


    private Instant createdAt;

    private Instant updatedAt;

    private Long createdBy;
    private Long LastModifiedBy;

    public EcgDto(
            Long id,
            String note,
            String noteFromConsultant,
            EcgStates state,
            List<File> files,
            String wardName,
            String bedNumber,
            Long patientId,
            String patientName,
            Instant date,
            String dx,
            String allergy,
            Gender gender,
            String age,
            Float weight,
            Float height,
            Long uploaderId,
            String uploaderName,
            Long createdBy,
            Long LastModifiedBy

    ) {
        this.id = id;
        this.note = note;
        this.noteFromConsultant = noteFromConsultant;
        this.state = state;
        this.files = files;
        this.wardName = wardName;
        this.bedNumber = bedNumber;
        this.patientId = patientId;
        this.patientName = patientName;
        this.date = date;
        this.dx = dx;
        this.allergy = allergy;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.uploaderId = uploaderId;
        this.uploaderName = uploaderName;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;

    }

    public EcgDto() {
    }
}
