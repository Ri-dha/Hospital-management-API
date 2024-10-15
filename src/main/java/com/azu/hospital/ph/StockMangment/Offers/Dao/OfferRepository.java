package com.azu.hospital.ph.StockMangment.Offers.Dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface OfferRepository extends JpaRepository<Offer, Integer> {




    @Query("SELECT o FROM Offer o JOIN o.offerDrugs d ORDER BY d.searchCount DESC")
    List<Offer> findMostSearched(Pageable pageable);

    @Query("SELECT o FROM Offer o JOIN o.offerDrugs d ORDER BY d.favorCount DESC")
    List<Offer> findMostFavored(Pageable pageable);

    @Query("SELECT DISTINCT o FROM Offer o JOIN o.offerDrugs d JOIN o.offerConsumables c WHERE " +
            "LOWER(d.druOfferTradeName) LIKE LOWER(CONCAT('%',:druOfferTradeName,'%')) OR " +
            "LOWER(d.druOfferScientificName) LIKE LOWER(CONCAT('%',:druOfferScientificName,'%')) OR " +
            "LOWER(c.consumableOfferTradeName) LIKE LOWER(CONCAT('%',:consumableOfferTradeName,'%')) OR " +
            "LOWER(d.drugOfferBarcode) LIKE LOWER(CONCAT('%',:drugOfferBarcode,'%')) OR " +
            "LOWER(c.consumableOfferBarcode) LIKE LOWER(CONCAT('%',:consumableOfferBarcode,'%')) OR " +
            "LOWER(o.company) LIKE LOWER(CONCAT('%',:companyName,'%')) OR " +
            "LOWER(o.store) LIKE LOWER(CONCAT('%',:supplierName,'%'))")
    List<Offer> findOffers(@Param("druOfferTradeName") String druOfferTradeName,
                           @Param("druOfferScientificName") String druOfferScientificName,
                           @Param("consumableOfferTradeName") String consumableOfferTradeName,
                           @Param("drugOfferBarcode") String drugOfferBarcode,
                           @Param("consumableOfferBarcode") String consumableOfferBarcode,
                           @Param("companyName") String companyName,
                           @Param("supplierName") String supplierName,
                           Pageable pageable);


}
