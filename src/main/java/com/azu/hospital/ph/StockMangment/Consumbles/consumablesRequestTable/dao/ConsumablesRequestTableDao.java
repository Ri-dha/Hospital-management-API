package com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.dao;

import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.entity.ConsumablesRequestTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ConsumablesRequestTableDao {

    void addNewRequestConsumable(ConsumablesRequestTable request);

    void updateExistingRequest(ConsumablesRequestTable update);

    Optional<ConsumablesRequestTable> getRequestById(Long requestId);

    Page<ConsumablesRequestTable> getAllRequests( Pageable pageable);


    void addNewRequests(List<ConsumablesRequestTable> requests);


}
