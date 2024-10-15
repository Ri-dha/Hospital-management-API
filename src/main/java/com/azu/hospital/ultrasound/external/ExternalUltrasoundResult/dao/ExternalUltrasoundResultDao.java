package com.azu.hospital.ultrasound.external.ExternalUltrasoundResult.dao;

import com.azu.hospital.ultrasound.external.ExternalUltrasoundResult.entity.ExternalUltrasoundResult;

import java.util.Optional;

public interface ExternalUltrasoundResultDao {

    ExternalUltrasoundResult createNewResult(ExternalUltrasoundResult internalUltrasoundResult);

    Optional<ExternalUltrasoundResult> getByTestId(Long testId);

}
