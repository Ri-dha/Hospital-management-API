package com.azu.hospital.Patients.charts.pedaitric_charts.doctor_tour.dto;


import com.azu.hospital.Patients.PrematureBaby.dto.PrematureBabyDto;
import com.azu.hospital.Patients.charts.physical_assessment_enm.DoctorTourShift;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class BabyDoctorTourDto {

    private Long id;

    private String note;

    private String medicalDx;
    private String tourDetails;
    private String followUpNote;
    private Long prematureBabyId;
    private String prematureBabyName;

    private Long motherId;
    private String motherName;


    private Long createdBy;
    private Long updatedBy;

    private Instant createdAt;
    private Instant updateAt;

    public BabyDoctorTourDto(Long id,  String note,  String medicalDx, String tourDetails, String followUpNote, Long prematureBabyId, String prematureBabyName, Long motherId, String motherName, Long createdBy, Long updatedBy, Instant createdAt, Instant updateAt) {
        this.id = id;
        this.note = note;
        this.medicalDx = medicalDx;
        this.tourDetails = tourDetails;
        this.followUpNote = followUpNote;
        this.prematureBabyId = prematureBabyId;
        this.prematureBabyName = prematureBabyName;
        this.motherId = motherId;
        this.motherName = motherName;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }
}
