package com.azu.hospital.ecg.external.dto;

import com.azu.hospital.ecg.external.entity.ExternalEcg;
import org.springframework.stereotype.Service;

@Service
public class ExternalEcgDtoMapper {
    public ExternalEcgDto toDto(ExternalEcg ecg){
        return new ExternalEcgDto(
                ecg.getId(),
                ecg.getPatientData(),
                ecg.getNurse() == null ? null :  ecg.getNurse().getId(),
                ecg.getNurse()== null ? null: ecg.getNurse().getUsername(),
                ecg.getState(),
                ecg.getFiles(),
                ecg.getCreatedAt(),
                ecg.getUpdatedAt()
        );
    }
}
