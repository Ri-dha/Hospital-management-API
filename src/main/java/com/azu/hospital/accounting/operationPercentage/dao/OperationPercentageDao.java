package com.azu.hospital.accounting.operationPercentage.dao;

import com.azu.hospital.accounting.operationPercentage.entity.OperationPercentage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OperationPercentageDao {

    void createNewOperationPercentage(OperationPercentage operationPercentage);

    void updateOperationPercentage(OperationPercentage  operationPercentage);

    Optional<OperationPercentage> getOperationPercentageById(Long id);
    Page<OperationPercentage> getAllOperationPercentages(Pageable pageable);
    void deleteOperationPercentage(OperationPercentage operationPercentage);

}
