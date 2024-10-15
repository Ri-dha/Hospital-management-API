package com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.dao;

import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.ConsumablesRequestTableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("consumablesRequestTableListJpa")
public class ConsumablesRequestTableListDataAccess implements ConsumablesRequestTableListDao {

    private final ConsumablesRequestTableListRepository repository;

    @Autowired
    public ConsumablesRequestTableListDataAccess(@Qualifier("consumablesRequestTableListRepository") ConsumablesRequestTableListRepository consumablesRequestTableListRepository) {
        this.repository = consumablesRequestTableListRepository;
    }


    @Override
    public void createConsumablesRequestTableList(ConsumablesRequestTableList consumablesRequestTableList) {
        repository.save(consumablesRequestTableList);
    }

    @Override
    public void updateConsumablesRequestTableList(ConsumablesRequestTableList consumablesRequestTableList) {
        repository.save(consumablesRequestTableList);
    }

    @Override
    public void deleteConsumablesRequestTableList(Long requestListId) {
        repository.deleteById(requestListId);
    }

    @Override
    public Page<ConsumablesRequestTableList> getConsumablesRequestTableList(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<ConsumablesRequestTableList> getConsumablesRequestTableListById(Long requestListId) {
        return repository.findById(requestListId);
    }

    @Override
    public Page<ConsumablesRequestTableList> getAllByWardOrUnit(Long wardId, Long unitId, Pageable pageable) {
        return repository.getAllByWardOrUnit(wardId, unitId, pageable);
    }


}
