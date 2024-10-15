package com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.dao;

import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.ConsumablesRequestTableList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ConsumablesRequestTableListDao {

    void createConsumablesRequestTableList (ConsumablesRequestTableList consumablesRequestTableList);

    void updateConsumablesRequestTableList (ConsumablesRequestTableList consumablesRequestTableList);

    void deleteConsumablesRequestTableList (Long requestListId);

    Page<ConsumablesRequestTableList> getConsumablesRequestTableList (Pageable pageable);

    Optional<ConsumablesRequestTableList> getConsumablesRequestTableListById (Long requestListId);
    Page<ConsumablesRequestTableList> getAllByWardOrUnit(Long wardId, Long unitId, Pageable pageable);

}
