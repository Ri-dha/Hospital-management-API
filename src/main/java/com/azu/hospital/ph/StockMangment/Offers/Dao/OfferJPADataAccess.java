package com.azu.hospital.ph.StockMangment.Offers.Dao;


import com.azu.hospital.ph.StockMangment.Offers.list.DrugsOfferRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("offerJpa")
@Qualifier("offerJpa")
public class OfferJPADataAccess implements OfferDao {
    private final OfferRepository offerRepository;
    private final DrugsOfferRepository drugsOfferRepository;

    public OfferJPADataAccess(OfferRepository offerRepository, DrugsOfferRepository drugsOfferRepository) {
        this.offerRepository = offerRepository;
        this.drugsOfferRepository = drugsOfferRepository;
    }

    @Override
    public Page<Offer> getAllOffers(Pageable pageable) {
        return offerRepository.findAll(pageable);
    }

    @Override
    public Offer addNewOffer(Offer request) {
        return offerRepository.save(request);
    }

    @Override
    public Offer updateExistOffer(Offer update) {
        return offerRepository.save(update);
    }

    @Override
    public void deleteOffer(Integer offerId) {
      offerRepository.deleteById(offerId);
    }

    @Override
    public Optional<Offer> findOfferById(Integer offerId) {
        return offerRepository.findById(offerId);
    }

    @Override
    public boolean existOfferById(Integer offerId) {
        return offerRepository.existsById(offerId);
    }

    @Override
    public void incrementSearchCount(Integer id) {
        drugsOfferRepository.incrementSearchCount(id);
    }

    @Override
    public void incrementFavorCount(Integer id) {
      drugsOfferRepository.incrementFavorCount(id);
    }

    @Override
    public List<Offer> findOffers(String druOfferTradeName, String druOfferScientificName, String consumableOfferTradeName, String drugOfferBarcode, String consumableOfferBarcode, String companyName, String supplierName, Pageable pageable) {
        return offerRepository.findOffers(druOfferTradeName,druOfferScientificName,consumableOfferTradeName,drugOfferBarcode,consumableOfferBarcode,companyName,supplierName,pageable );
    }

    @Override
    public Long countAllOffers() {
        return offerRepository.count();
    }

    @Override
    public List<Offer> findMostSearched(Pageable pageable) {
        return offerRepository.findMostSearched(pageable);
    }

    @Override
    public List<Offer> findMostFavored(Pageable pageable) {
        return offerRepository.findMostFavored(pageable);
    }
}
