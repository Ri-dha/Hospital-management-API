package com.azu.hospital.consultant.consultantPatient.dto;

import com.azu.hospital.consultant.consultantPatient.request.ConsultantPatientAddress;
import com.azu.hospital.consultant.consultantPatient.request.ConsultantPatientInfo;
import com.azu.hospital.consultant.consultantPatient.request.ConsultantPatientJobInfo;
import com.azu.hospital.utils.files.File;
import com.azu.hospital.utils.image.Image;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConsultantPatientDto {

    private Long id;

    private ConsultantPatientInfo consultantPatientInfo;

    private ConsultantPatientAddress consultantPatientAddress;

    private ConsultantPatientJobInfo jobInfo;

    private String note;

    private Long doctorId;

    private String doctorName;

    private List<File> files;

    private List<Image> images;

    public ConsultantPatientDto(
            Long id,
            ConsultantPatientInfo consultantPatientInfo,
            ConsultantPatientAddress consultantPatientAddress,
            ConsultantPatientJobInfo jobInfo,
            String note,
            Long doctorId,
            String doctorName,
            List<File> files,
            List<Image> images
    ) {
        this.id = id;
        this.consultantPatientInfo = consultantPatientInfo;
        this.consultantPatientAddress = consultantPatientAddress;
        this.jobInfo = jobInfo;
        this.note = note;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.files = files;
        this.images = images;
    }
}
