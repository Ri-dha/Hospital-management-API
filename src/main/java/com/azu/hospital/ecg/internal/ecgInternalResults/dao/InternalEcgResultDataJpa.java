package com.azu.hospital.ecg.internal.ecgInternalResults.dao;


import com.azu.hospital.ecg.internal.ecgInternalResults.entity.InternalEcgResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("internalEcgResultRepo")
public class InternalEcgResultDataJpa implements InternalEcgResultDao {

    private final InternalEcgResultRepository internalEcgResultRepository;

    @Autowired
    public InternalEcgResultDataJpa(InternalEcgResultRepository internalEcgResultRepository) {
        this.internalEcgResultRepository = internalEcgResultRepository;
    }


    @Override
    public InternalEcgResult createNewResult(InternalEcgResult internalEcgResult) {
        return internalEcgResultRepository.save(internalEcgResult);
    }

    @Override
    public Optional<InternalEcgResult> getByTestId(Long testId) {
        return internalEcgResultRepository.findByEcgId(testId);

    }

    @Override
    public void updateResult(InternalEcgResult internalEcgResult) {
        internalEcgResultRepository.save(internalEcgResult);
    }
}
