package com.azu.hospital.ecg.internal.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class InternalEcgTestRequest {

    @NotNull(message = "patientId is required")
    private Long patientId;

    private String note;

    @NotEmpty
    private List<MultipartFile> files;


    public InternalEcgTestRequest() {
    }

    public InternalEcgTestRequest(Long patientId, String note, List<MultipartFile> files) {
        this.patientId = patientId;
        this.note = note;
        this.files = files;
    }
}
