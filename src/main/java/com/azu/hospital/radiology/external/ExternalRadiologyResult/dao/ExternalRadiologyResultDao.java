package com.azu.hospital.radiology.external.ExternalRadiologyResult.dao;

import com.azu.hospital.radiology.external.ExternalRadiologyResult.entity.ExternalRadiologyResult;

import java.util.Optional;

public interface ExternalRadiologyResultDao {

    ExternalRadiologyResult createNewResult(ExternalRadiologyResult internalRadiologyResult);

    Optional<ExternalRadiologyResult> getByTestId(Long testId);



}
