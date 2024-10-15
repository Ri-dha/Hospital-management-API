package com.azu.hospital.ecg.external.request;

import com.azu.hospital.ecg.external.entity.EcgPatientData;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ExternalAddResultToEcgTest {

    @NotNull
    private Long ecgId;

    @NotNull
    @NotEmpty
    private List<MultipartFile> files;

    private String note;




    public ExternalAddResultToEcgTest() {
    }
}
