package com.azu.hospital.ph.StockMangment.addOtherItems.dao;

import com.azu.hospital.utils.enums.DeviceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface OtherItemDao {


    Page<OtherItems> findByItemSortedBy(
            String itemName,
            String itemCompany,
            String itemBarcode,
            DeviceType type,
            Pageable pageable);


    Optional<OtherItems> selectById(Long itemId);



    OtherItems createItem(OtherItems otherItems);

    void updateItems(OtherItems updateRequest);

    boolean existsById(Long itemId);

    boolean existByItemBarcode(String itemBarcode);

    void deleteItemById(Long itemId);


    List<OtherItems> getItemsListByBillId(Integer billId);


}
