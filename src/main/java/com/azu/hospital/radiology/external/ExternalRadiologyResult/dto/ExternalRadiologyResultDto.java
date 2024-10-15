package com.azu.hospital.radiology.external.ExternalRadiologyResult.dto;

import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import com.azu.hospital.utils.files.File;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExternalRadiologyResultDto {

    private Long id;

    private String note; // TODO: 12/29/2023 this not should be handle as Text

    private Long testId;

    private List<File> files;

    private RadiologyTypeEnum type;

    // TODO: 12/29/2023 you should return patient data with the result for front end
    // TODO: 1/1/2024 Not here in main test this result of test that get it by test id

    public ExternalRadiologyResultDto(Long id, String note, Long testId, List<File> files,RadiologyTypeEnum type) {
        this.id = id;
        this.note = note;
        this.testId = testId;
        this.files = files;
        this.type=type;
    }

    public ExternalRadiologyResultDto() {

    }


}
