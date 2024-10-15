package com.azu.hospital.consultant.consultantPatient.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CreateNewPatientRequest {

    @NotNull
    @Valid
    private ConsultantPatientInfo consultantPatientInfo;


    @NotNull
    @Valid
    private ConsultantPatientAddress consultantPatientAddress;


    @NotNull
    @Valid
    private ConsultantPatientJobInfo consultantPatientJobInfo;

    private Long doctorId;

    private String note ;

    private List<MultipartFile> ids;

    private List<MultipartFile> files;

    public CreateNewPatientRequest() {
    }
}
