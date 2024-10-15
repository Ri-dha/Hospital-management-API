package com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.dao;

import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.ConsumablesRequestTableList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsumablesRequestTableListRepository extends JpaRepository<ConsumablesRequestTableList, Long> {


//    @Query("SELECT c FROM ConsumablesRequestTableList c")
//    Page<ConsumablesRequestTableList> getConsumablesRequestTableList (Pageable pageable);
//


    @Query("SELECT c FROM ConsumablesRequestTableList c WHERE c.ward.wardId = :wardId OR c.unit.unitId = :unitId")
    Page<ConsumablesRequestTableList> getAllByWardOrUnit(Long wardId, Long unitId, Pageable pageable);

}
