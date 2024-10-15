package com.azu.hospital.radiology.external.ExternalRadiologyResult.dao;

import com.azu.hospital.radiology.external.ExternalRadiologyResult.entity.ExternalRadiologyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("externalRadiologyResultRepo")
public class ExternalRadiologyResultDataJpa implements ExternalRadiologyResultDao {

    private final ExternalRadiologyResultRepository externalRadiologyResultRepository;

    @Autowired
    public ExternalRadiologyResultDataJpa(ExternalRadiologyResultRepository externalRadiologyResultRepository) {
        this.externalRadiologyResultRepository = externalRadiologyResultRepository;
    }

    @Override
    public ExternalRadiologyResult createNewResult(ExternalRadiologyResult externalRadiologyResult) {
        return externalRadiologyResultRepository.save(externalRadiologyResult);
    }

    @Override
    public Optional<ExternalRadiologyResult> getByTestId(Long testId) {
        return externalRadiologyResultRepository.findByExternalRadiologyTestId(testId);
    }
}
