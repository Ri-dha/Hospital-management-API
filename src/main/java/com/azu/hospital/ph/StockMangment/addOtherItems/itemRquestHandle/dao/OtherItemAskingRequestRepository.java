package com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.dao;

import com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.entity.OtherItemAskingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface OtherItemAskingRequestRepository extends JpaRepository<OtherItemAskingRequest, Long> {

    @Query("SELECT o FROM OtherItemAskingRequest o WHERE o.requestId = :requestId")
    Optional<OtherItemAskingRequest>findByRequestId(@Param("requestId") Long requestId);
}
