package com.azu.hospital.ph.StockMangment.Offers.Dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OfferDao {

    Page<Offer> getAllOffers(Pageable pageable);

    Offer addNewOffer(Offer request);

    Offer updateExistOffer(Offer update);

    void deleteOffer(Integer offerId);

    Optional<Offer> findOfferById(Integer offerId);

    boolean existOfferById(Integer offerId);

    void incrementSearchCount(Integer id);

    void incrementFavorCount(Integer id);

    List<Offer> findMostSearched(Pageable pageable);

    List<Offer> findMostFavored(Pageable pageable);

    List<Offer> findOffers(@Param("druOfferTradeName") String druOfferTradeName,
                           @Param("druOfferScientificName") String druOfferScientificName,
                           @Param("consumableOfferTradeName") String consumableOfferTradeName,
                           @Param("drugOfferBarcode") String drugOfferBarcode,
                           @Param("consumableOfferBarcode") String consumableOfferBarcode,
                           @Param("companyName") String companyName,
                           @Param("supplierName") String supplierName, Pageable pageable);


    Long countAllOffers();
}
