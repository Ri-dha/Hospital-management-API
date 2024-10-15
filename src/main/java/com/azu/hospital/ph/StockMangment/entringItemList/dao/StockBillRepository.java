package com.azu.hospital.ph.StockMangment.entringItemList.dao;

import com.azu.hospital.ph.StockMangment.Consumbles.entity.Consumables;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItems;
import com.azu.hospital.ph.StockMangment.entringItemList.entity.StockBill;
import com.azu.hospital.ph.main_data_store.drugs_item.entity.DrugsItem;
import com.azu.hospital.utils.enums.StockBillState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
public interface StockBillRepository extends JpaRepository<StockBill, Long> {


    @Query("SELECT b FROM StockBill b WHERE " +
            "(b.billState = :state OR :state IS NULL) AND " +
            "(b.supplierDetails LIKE %:supplierDetails% OR :supplierDetails IS NULL) " +
            "AND (LOWER(b.pharmacyName) = LOWER(:pharmacyName) OR :pharmacyName IS NULL) " +
            "AND (b.billEntryDate = :billEntryDate OR :billEntryDate IS NULL) " +
            "AND (b.billNumber = :billNumber OR :billNumber IS NULL)")
    Page<StockBill> findBillsBy(
            @Param("supplierDetails") String supplierDetails,
            @Param("pharmacyName") String pharmacyName,
            @Param("billEntryDate") String billEntryDate,
            @Param("billNumber") BigDecimal billNumber,
            @Param("state") StockBillState state,
            Pageable pageable
    );


    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM StockBill b LEFT JOIN b.drugs d WHERE d IN :drugs")
    boolean isBillContainDrugsListOrNull(@Param("drugs") List<DrugsItem> drugs);


    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM StockBill b LEFT JOIN b.consumables c WHERE c IN :consumables")
    boolean isBillContainConsumablesOrNull(@Param("consumables") List<Consumables> consumables);


    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM StockBill b LEFT JOIN b.items i WHERE i IN :items")
    boolean isBillContainItemsOrNull(@Param("items") List<OtherItems> items);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM StockBill b WHERE b.supplierDetails = :supplierDetails " +
            "AND b.billNumber = :billNumber AND b.billTotalPrice = :billTotalPrice")
    boolean findBillBy(@Param("supplierDetails") String supplierDetails,
                       @Param("billNumber") BigDecimal billNumber,
                       @Param("billTotalPrice") BigDecimal billTotalPrice);



    @Query("SELECT b FROM StockBill b WHERE MONTH(to_date(b.billDate , 'YYYY-MM-DD') ) = :month AND  YEAR(to_date(b.billDate , 'YYYY-MM-DD')) = :year AND :type = 'month' OR YEAR(to_date(b.billDate , 'YYYY-MM-DD')) = :year AND :type = 'year' ")
    List<StockBill> findAllByMonthAndYear(Integer month, Integer year, String type);



    @Query("SELECT sb FROM StockBill sb LEFT JOIN sb.drugs d LEFT JOIN sb.consumables c LEFT JOIN sb.items i WHERE " +
            "(d IS NOT NULL AND :item = 'drug') OR " +
            "(c IS NOT NULL AND :item = 'consumable') OR " +
            "(i IS NOT NULL AND :item = 'other')")
    Page<StockBill> findAllByItemContaining(@Param("item") String type, Pageable pageable);



    @Transactional
    @Modifying
    @Query("DELETE FROM DrugsItem d WHERE d.id = :itemId AND d.bill.billId = :billId")
    void deleteDrugByIdAndBillId(@Param("itemId") Long itemId, @Param("billId") Long billId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Consumables c WHERE c.consumableId = :itemId AND c.bill.billId = :billId")
    void deleteConsumableByIdAndBillId(@Param("itemId") Long itemId, @Param("billId") Long billId);

    @Transactional
    @Modifying
    @Query("DELETE FROM OtherItems o WHERE o.itemId = :itemId AND o.bill.billId = :billId")
    void deleteOtherItemByIdAndBillId(@Param("itemId") Long itemId, @Param("billId") Long billId);

}
