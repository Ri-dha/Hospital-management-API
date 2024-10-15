package com.azu.hospital.ultrasound.external.ExternalUltrasoundResult.dao;

import com.azu.hospital.ultrasound.external.ExternalUltrasoundResult.entity.ExternalUltrasoundResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("externalUltrasoundResultRepo")
public class ExternalUltrasoundResultDataJpa implements ExternalUltrasoundResultDao {

    private final ExternalUltrasoundResultRepository externalUltrasoundResultRepository;

    @Autowired
    public ExternalUltrasoundResultDataJpa(ExternalUltrasoundResultRepository externalUltrasoundResultRepository) {
        this.externalUltrasoundResultRepository = externalUltrasoundResultRepository;
    }

    @Override
    public ExternalUltrasoundResult createNewResult(ExternalUltrasoundResult externalUltrasoundResult) {
        return externalUltrasoundResultRepository.save(externalUltrasoundResult);
    }

    @Override
    public Optional<ExternalUltrasoundResult> getByTestId(Long testId) {
        return externalUltrasoundResultRepository.findByExternalUltrasoundTestId(testId);
    }
}
