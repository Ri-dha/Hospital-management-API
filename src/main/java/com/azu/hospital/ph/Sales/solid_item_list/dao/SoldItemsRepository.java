package com.azu.hospital.ph.Sales.solid_item_list.dao;

import com.azu.hospital.ph.Sales.solid_item_list.entity.SoldItems;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SoldItemsRepository extends JpaRepository<SoldItems, Long> {



    @Query("SELECT i.itemName, SUM(i.quantity) FROM SoldItems i GROUP BY i.itemName ORDER BY SUM(i.quantity) DESC")
    List<Object[]> findMostSoldItems(Pageable pageable);


    @Query(value = "SELECT * FROM sold_item_list si WHERE si.sold_item_id = :billId", nativeQuery = true)
    List<SoldItems> findAllBySoldBillBillId(Long billId);
}
