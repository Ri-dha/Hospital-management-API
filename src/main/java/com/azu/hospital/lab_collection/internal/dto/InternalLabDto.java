package com.azu.hospital.lab_collection.internal.dto;

import com.azu.hospital.utils.enums.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class InternalLabDto {

    private Long id;

    private Instant createdAt;

    private String state;

    private String note;

    private Long uploaderId;

    private String uploaderName;

    private List<String> uploaderRole;

    private Long accepterId;

    private String accepterName;

    private List<String> accepterRole;

    private Long rejecterId;

    private String rejecterName;

    private List<String> rejecterRole;

    private Long patientId;

    private String patientName;

    private Gender pateintGender;

    private String patientAge;


    private String wardName;

    private Float patientWeight;

    private Float patientHeight;

    private String bedNumber;

    private Long createdBy;
    private Long LastModifiedBy;

    public InternalLabDto(
            Long id,
            Instant createdAt,
            String state,
            String note,
            Long uploaderId,
            String uploaderName,
            List<String> uploaderRole,
            Long accepterId,
            String accepterName,
            List<String> accepterRole,
            Long rejecterId,
            String rejecterName,
            List<String> rejecterRole,
            Long patientId,
            String patientName,
            Gender pateintGender,
            String wardName,
            Float patientWeight,
            Float patientHeight,
            String bedNumber,
            String patientAge,
            Long createdBy,
            Long LastModifiedBy

    ) {
        this.id = id;
        this.createdAt = createdAt;
        this.state = state;
        this.note = note;
        this.uploaderId = uploaderId;
        this.uploaderName = uploaderName;
        this.uploaderRole = uploaderRole;
        this.accepterId = accepterId;
        this.accepterName = accepterName;
        this.accepterRole = accepterRole;
        this.rejecterId = rejecterId;
        this.rejecterName = rejecterName;
        this.rejecterRole = rejecterRole;
        this.patientId = patientId;
        this.patientName = patientName;
        this.pateintGender = pateintGender;
        this.wardName = wardName;
        this.patientWeight = patientWeight;
        this.patientHeight = patientHeight;
        this.bedNumber = bedNumber;
        this.patientAge = patientAge;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;

    }


}
