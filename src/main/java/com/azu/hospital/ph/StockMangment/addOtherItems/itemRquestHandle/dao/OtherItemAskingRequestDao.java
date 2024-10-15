package com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.dao;

import com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.entity.OtherItemAskingRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OtherItemAskingRequestDao {


    void addNewRequestItem(OtherItemAskingRequest request);

    void updateExistingRequest(OtherItemAskingRequest update);

    Optional<OtherItemAskingRequest> getRequestById(Long requestId);

    Page<OtherItemAskingRequest> getAllRequests(Pageable pageable);
}
