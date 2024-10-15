package com.azu.hospital.ultrasound.internal.InternalUltrasoundResult.dao;

import com.azu.hospital.ultrasound.internal.InternalUltrasoundResult.entity.InternalUltrasoundResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("internalUltrasoundResultRepo")
public class InternalUltrasoundResultDataJpa implements InternalUltrasoundResultDao {

    private final InternalUltrasoundResultRepository internalUltrasoundResultRepository;

    @Autowired
    public InternalUltrasoundResultDataJpa(InternalUltrasoundResultRepository internalUltrasoundResultRepository) {
        this.internalUltrasoundResultRepository = internalUltrasoundResultRepository;
    }

    @Override
    public InternalUltrasoundResult createNewResult(InternalUltrasoundResult internalUltrasoundResult) {
        return internalUltrasoundResultRepository.save(internalUltrasoundResult);
    }

    @Override
    public Optional<InternalUltrasoundResult> getByTestId(Long testId) {
        return internalUltrasoundResultRepository.findByInternalUltrasoundTestId(testId);
    }
}
