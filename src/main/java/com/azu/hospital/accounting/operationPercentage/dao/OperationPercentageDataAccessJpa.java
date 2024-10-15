package com.azu.hospital.accounting.operationPercentage.dao;

import com.azu.hospital.accounting.operationPercentage.entity.OperationPercentage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("operationPercentageJpa")
public class OperationPercentageDataAccessJpa implements OperationPercentageDao{

    private final OperationPercentageRepository repository;

    public OperationPercentageDataAccessJpa(OperationPercentageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createNewOperationPercentage(OperationPercentage operationPercentage) {
        repository.save(operationPercentage);
    }

    @Override
    public void updateOperationPercentage(OperationPercentage operationPercentage) {
        repository.save(operationPercentage);
    }


    @Override
    public Optional<OperationPercentage> getOperationPercentageById(Long id) {
        return repository.getOperationPercentageByOperationId(id);
    }

    @Override
    public Page<OperationPercentage> getAllOperationPercentages(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void deleteOperationPercentage(OperationPercentage operationPercentage) {
        repository.delete(operationPercentage);
    }


}
