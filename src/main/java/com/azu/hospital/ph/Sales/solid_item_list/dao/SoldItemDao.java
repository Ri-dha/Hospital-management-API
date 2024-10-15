package com.azu.hospital.ph.Sales.solid_item_list.dao;

import com.azu.hospital.ph.Sales.solid_item_list.entity.SoldItems;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SoldItemDao {

    void addItemList(List<SoldItems> items);

    void updateExistItem(List<SoldItems>  update);

    Page<SoldItems> getAllItemLists(Pageable pageable);

    Optional<SoldItems> getItemFormListById(Long id);

    List<SoldItems> getAllItemListByBillId(Long billId);

    void deleteSolidItemById(Long id);

}
