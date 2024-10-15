package com.azu.hospital.ecg.internal.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class InternalEcgTestRequestUpdate {

    private String note;

    @NotEmpty
    private List<MultipartFile> files;


    public InternalEcgTestRequestUpdate() {
    }

    public InternalEcgTestRequestUpdate( String note, List<MultipartFile> files) {
        this.note = note;
        this.files = files;
    }
}
