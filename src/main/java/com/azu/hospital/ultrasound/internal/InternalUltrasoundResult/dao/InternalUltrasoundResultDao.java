package com.azu.hospital.ultrasound.internal.InternalUltrasoundResult.dao;

import com.azu.hospital.ultrasound.internal.InternalUltrasoundResult.entity.InternalUltrasoundResult;

import java.util.Optional;

public interface InternalUltrasoundResultDao {

    InternalUltrasoundResult createNewResult(InternalUltrasoundResult internalUltrasoundResult);

    Optional<InternalUltrasoundResult> getByTestId(Long testId);

}
