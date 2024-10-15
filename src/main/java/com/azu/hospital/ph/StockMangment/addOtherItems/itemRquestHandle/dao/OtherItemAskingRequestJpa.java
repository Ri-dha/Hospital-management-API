package com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.dao;

import com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.entity.OtherItemAskingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("AskingRequest")
public class OtherItemAskingRequestJpa implements OtherItemAskingRequestDao{


    private final OtherItemAskingRequestRepository repository;

    @Autowired
    public OtherItemAskingRequestJpa(OtherItemAskingRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addNewRequestItem(OtherItemAskingRequest request) {
        repository.save(request);
    }

    @Override
    public void updateExistingRequest(OtherItemAskingRequest update) {
        repository.save(update);
    }

    @Override
    public Optional<OtherItemAskingRequest> getRequestById(Long requestId) {
        return repository.findByRequestId(requestId);
    }

    @Override
    public Page<OtherItemAskingRequest> getAllRequests(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
