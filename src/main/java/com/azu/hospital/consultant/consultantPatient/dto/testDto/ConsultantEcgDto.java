package com.azu.hospital.consultant.consultantPatient.dto.testDto;


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
public class ConsultantEcgDto {

    private Long id;

    private Long patientId;

    private String patientName;

    private Long nurseId;

    private String nurseName;

    private Long doctorId;

    private String doctorName;

    private EcgStates state;

    private List<File> files;

    private String note;

    private Instant date;



    public ConsultantEcgDto(
            Long id,
            Long patientId,
            String patientName,
            Long nurseId,
            String nurseName,
            Long doctorId,
            String doctorName,
            EcgStates state,
            List<File> files,
            Instant date,
            String note

    ) {
        this.id = id;
        this.patientId = patientId;
        this.patientName = patientName;
        this.nurseId = nurseId;
        this.nurseName = nurseName;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.state = state;
        this.files = files;
        this.date=date;
        this.note = note;
    }

    public ConsultantEcgDto() {
    }
}
