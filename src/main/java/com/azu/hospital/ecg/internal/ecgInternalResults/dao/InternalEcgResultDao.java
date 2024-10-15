package com.azu.hospital.ecg.internal.ecgInternalResults.dao;

import com.azu.hospital.ecg.internal.ecgInternalResults.entity.InternalEcgResult;

import java.util.Optional;

public interface InternalEcgResultDao {

    InternalEcgResult createNewResult(InternalEcgResult internalEcgResult);

    Optional<InternalEcgResult> getByTestId(Long testId);

    void updateResult(InternalEcgResult internalEcgResult);
}
