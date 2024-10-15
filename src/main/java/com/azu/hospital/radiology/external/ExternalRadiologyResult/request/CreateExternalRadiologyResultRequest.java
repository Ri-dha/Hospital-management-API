package com.azu.hospital.radiology.external.ExternalRadiologyResult.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CreateExternalRadiologyResultRequest {




    @NotNull(message = "test id can not be null")
    private Long testId;

    @NotNull(message = "note can not be null")
    @NotBlank(message = "note can not be blank")
    private String note;

    @NotEmpty(message = "files can not be empty")
    private List<MultipartFile> files;

    public CreateExternalRadiologyResultRequest() {
    }
}
