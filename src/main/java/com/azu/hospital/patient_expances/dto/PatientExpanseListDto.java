package com.azu.hospital.patient_expances.dto;

import com.azu.hospital.utils.enums.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class PatientExpanseListDto {
    private Long listId;
    private Long patientId;
    private String patientName;
    private String doctorName;
    private Long doctorId;
    private String ward;
    private String bed;
    private Gender gender;
    private String age;
    private String nurseName;
    private Long nurseId;
    private Instant createAt;
    private Instant updateAt;

    private List<PatientExpanseDto> patientExpanseList = new ArrayList<>();

    public PatientExpanseListDto() {
    }

    public PatientExpanseListDto(Long listId,Long patientId, String patientName, String doctorName,
                                 Long doctorId, String ward, String bed, Gender gender, String age,
                                 String nurseName, Long nurseId, List<PatientExpanseDto> patientExpanseList,
                                 Instant createAt,Instant updateAt
    ) {
        this.listId = listId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.doctorId = doctorId;
        this.ward = ward;
        this.bed = bed;
        this.gender = gender;
        this.age = age;
        this.nurseName = nurseName;
        this.nurseId = nurseId;
        this.patientExpanseList = patientExpanseList;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
