package com.azu.hospital.ultrasound.internal.dto;

import com.azu.hospital.utils.enums.Gender;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class InternalUltrasoundTestDto {

    private Long id;

    private String note;

    private UltrasoundTypeEnum type;

    private UltrasoundOrderState state;

    private Long uploaderId;

    private String uploaderName;

    private Long accepterId;

    private String accepterName;

    private Long rejecterId;

    private String rejecterName;

    private String wardName;

    private String bedNumber;

    private Long patientId;

    private String patientName;

    private String nurseName;

    private Instant date;

    private String dx;

    private String allergy;

    private Gender gender;

    private String age;

    private Float weight;

    private Float height;

    private Long createdBy;
    private Long LastModifiedBy;

    public InternalUltrasoundTestDto(
            Long id,
            String note,
            UltrasoundTypeEnum type,
            UltrasoundOrderState state,
            String wardName,
            String bedNumber,
            Long patientId,
            String patientName,
            String nurseName,
            Instant date,
            String dx,
            String allergy,
            Gender gender,
            String age,
            Float weight,
            Float height,
            Long uploaderId,
            String uploaderName,
            Long accepterId,
            String accepterName,
            Long rejecterId,
            String rejecterName,
            Long createdBy,
            Long LastModifiedBy
    ) {
        this.id = id;
        this.note = note;
        this.type = type;
        this.state = state;
        this.wardName = wardName;
        this.bedNumber = bedNumber;
        this.patientId = patientId;
        this.patientName = patientName;
        this.nurseName = nurseName;
        this.date = date;
        this.dx = dx;
        this.allergy = allergy;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.uploaderId = uploaderId;
        this.uploaderName = uploaderName;
        this.accepterId = accepterId;
        this.accepterName = accepterName;
        this.rejecterId = rejecterId;
        this.rejecterName = rejecterName;

        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
    }

    public InternalUltrasoundTestDto() {
    }
}
