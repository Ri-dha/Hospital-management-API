package com.azu.hospital.radiology.internal.InternalRadiologyResult.dao;

import com.azu.hospital.radiology.internal.InternalRadiologyResult.entity.InternalRadiologyResult;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;

import java.util.List;
import java.util.Optional;

public interface InternalRadiologyResultDao {

    InternalRadiologyResult createNewResult(InternalRadiologyResult internalRadiologyResult);

    Optional<InternalRadiologyResult> getByTestId(Long testId);

    List<InternalRadiologyResult> getAllResultsByPatientId(Long patientId, RadiologyTypeEnum radiologyType);

}
