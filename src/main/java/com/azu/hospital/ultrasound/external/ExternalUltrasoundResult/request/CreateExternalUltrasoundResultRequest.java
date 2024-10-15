package com.azu.hospital.ultrasound.external.ExternalUltrasoundResult.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CreateExternalUltrasoundResultRequest {

    @NotNull
    private Long testId;

    @NotNull
    @NotBlank
    private String note;

    @NotEmpty
    private List<MultipartFile> files;

    public CreateExternalUltrasoundResultRequest() {
    }
}
