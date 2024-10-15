package com.azu.hospital.accounting.operationPercentage.dao;

import com.azu.hospital.accounting.operationPercentage.entity.OperationPercentage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OperationPercentageRepository extends JpaRepository<OperationPercentage, Long>
{

    @Query("SELECT op FROM OperationPercentage op WHERE op.surgicalList.id = :operationId")
    Optional<OperationPercentage> getOperationPercentageByOperationId(Long operationId);


}
