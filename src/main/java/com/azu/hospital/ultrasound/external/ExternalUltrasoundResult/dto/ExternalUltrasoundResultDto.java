package com.azu.hospital.ultrasound.external.ExternalUltrasoundResult.dto;

import com.azu.hospital.utils.files.File;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExternalUltrasoundResultDto {

    private Long id;

    private String note;

    private Long testId;

    private List<File> files;

    public ExternalUltrasoundResultDto(Long id, String note, Long testId, List<File> files) {
        this.id = id;
        this.note = note;
        this.testId = testId;
        this.files = files;
    }

    public ExternalUltrasoundResultDto() {

    }
}
