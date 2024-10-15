package com.azu.hospital.ecg.internal.ecgInternalResults.dao;

import com.azu.hospital.ecg.internal.ecgInternalResults.entity.InternalEcgResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InternalEcgResultRepository extends JpaRepository<InternalEcgResult, Long> {

    @Query("SELECT ier FROM InternalEcgResult ier WHERE ier.ecg.id = :ecgId")
    Optional<InternalEcgResult> findByEcgId(@Param("ecgId") Long ecgId);

}
