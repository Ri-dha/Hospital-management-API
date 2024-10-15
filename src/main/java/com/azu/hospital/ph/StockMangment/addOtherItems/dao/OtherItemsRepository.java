package com.azu.hospital.ph.StockMangment.addOtherItems.dao;


import com.azu.hospital.utils.enums.DeviceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface OtherItemsRepository extends JpaRepository<OtherItems, Long> {

    @Query("SELECT d FROM OtherItems d WHERE " +
            "(:itemName IS NULL OR LOWER(d.itemName) LIKE LOWER(CONCAT('%', :itemName, '%'))) " +
            "AND (:itemCompany IS NULL OR LOWER(d.itemCompany) LIKE LOWER(CONCAT('%', :itemCompany, '%'))) " +
            "AND (:itemBarcode IS NULL OR d.itemBarcode LIKE CONCAT('%', :itemBarcode, '%')) " +
            "AND (:type IS NULL OR d.deviceType = : type) " +
            "ORDER BY d.updateAt DESC")
    Page<OtherItems> findByItemSortedBy(
            @Param("itemName") String itemName,
            @Param("itemCompany") String itemCompany,
            @Param("itemBarcode") String itemBarcode,
            @Param("type") DeviceType type,
            Pageable pageable);




    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM OtherItems b WHERE b.itemBarcode = :itemBarcode")
    boolean existByItemBarcode(@Param("itemBarcode") String itemBarcode);

    @Query("SELECT i FROM OtherItems i WHERE i.bill.billId = :billId")
    List<OtherItems> getItemsListByBillId(@Param("billId") Long billId);
}
