package com.azu.hospital.radiology.internal.InternalRadiologyResult.dao;

import com.azu.hospital.radiology.internal.InternalRadiologyResult.entity.InternalRadiologyResult;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("internalRadiologyResultRepo")
public class InternalRadiologyResultDataJpa implements InternalRadiologyResultDao {

    private final InternalRadiologyResultRepository internalRadiologyResultRepository;

    @Autowired
    public InternalRadiologyResultDataJpa(InternalRadiologyResultRepository internalRadiologyResultRepository) {
        this.internalRadiologyResultRepository = internalRadiologyResultRepository;
    }

    @Override
    public InternalRadiologyResult createNewResult(InternalRadiologyResult internalRadiologyResult) {
        return internalRadiologyResultRepository.save(internalRadiologyResult);
    }

    @Override
    public Optional<InternalRadiologyResult> getByTestId(Long testId) {
        return internalRadiologyResultRepository.findByInternalRadiologyTestId(testId);
    }

    @Override
    public List<InternalRadiologyResult> getAllResultsByPatientId(Long patientId, RadiologyTypeEnum radiologyType) {
        return internalRadiologyResultRepository.findAllByInternalRadiologyTest_PatientIdAndType(patientId, radiologyType);
    }
}
