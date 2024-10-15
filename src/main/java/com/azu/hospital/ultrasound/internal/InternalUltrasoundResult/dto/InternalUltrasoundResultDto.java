package com.azu.hospital.ultrasound.internal.InternalUltrasoundResult.dto;

import com.azu.hospital.utils.files.File;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InternalUltrasoundResultDto {

    private Long id;

    private String note;

    private Long testId;

    private List<File> files;

    public InternalUltrasoundResultDto(Long id, String note, Long testId, List<File> files) {
        this.id = id;
        this.note = note;
        this.testId = testId;
        this.files = files;
    }

    public InternalUltrasoundResultDto() {

    }
}
