package com.azu.hospital.radiology.internal.InternalRadiologyResult.dto;

import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import com.azu.hospital.utils.files.File;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class InternalRadiologyResultDto {

    private Long id;

    private String note;

    private Long testId;

    private List<File> files; // TODO: 12/29/2023 if the file image how can the frontend know

    private RadiologyTypeEnum type;

    private Instant createdAt;
    private Instant updatedAt;

    // TODO: 12/29/2023 missing patient data check design

    public InternalRadiologyResultDto(
            Long id,
            String note,
            Long testId,
            RadiologyTypeEnum type,
            List<File> files,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.note = note;
        this.testId = testId;
        this.type = type;
        this.files = files;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public InternalRadiologyResultDto() {

    }
}
