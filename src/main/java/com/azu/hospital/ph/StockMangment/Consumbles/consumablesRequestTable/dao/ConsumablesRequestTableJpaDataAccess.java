package com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.dao;

import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.entity.ConsumablesRequestTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("ConsumablesRequestTableJpa")
public class ConsumablesRequestTableJpaDataAccess implements ConsumablesRequestTableDao{
    private final ConsumablesRequestTableRepository repository;

    @Autowired
    public ConsumablesRequestTableJpaDataAccess(ConsumablesRequestTableRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addNewRequestConsumable(ConsumablesRequestTable request) {
        repository.save(request);
    }

    @Override
    public void updateExistingRequest(ConsumablesRequestTable update) {
        repository.save(update);
    }

    @Override
    public Optional<ConsumablesRequestTable> getRequestById(Long requestId) {
        return repository.findById(requestId);
    }

    @Override
    public Page<ConsumablesRequestTable> getAllRequests(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void addNewRequests(List<ConsumablesRequestTable> requests) {
        repository.saveAll(requests);
    }
}
