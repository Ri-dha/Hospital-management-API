package com.azu.hospital.ph.StockMangment.Offers.Dto;

import com.azu.hospital.ph.StockMangment.Company.CompanyDto;
import com.azu.hospital.ph.StockMangment.Company.CompanyDtoMapper;
import com.azu.hospital.ph.StockMangment.Offers.Dao.Offer;
import com.azu.hospital.ph.StockMangment.Offers.list.ConsumablesOffer;
import com.azu.hospital.ph.StockMangment.Offers.list.ConsumablesOfferDtoMapper;
import com.azu.hospital.ph.StockMangment.Offers.list.DrugOfferDtoMapper;
import com.azu.hospital.ph.StockMangment.Offers.list.DrugsOffer;
import com.azu.hospital.ph.StockMangment.Supplier.SupplierDto;
import com.azu.hospital.ph.StockMangment.Supplier.SupplierDtoMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferMapper {

    private final SupplierDtoMapper supplierDtoMapper;
    private final DrugOfferDtoMapper drugOfferDtoMapper;
    private final ConsumablesOfferDtoMapper consumablesOfferDtoMapper;
    private final CompanyDtoMapper companyDtoMapper;


    public OfferMapper(SupplierDtoMapper supplierDtoMapper, DrugOfferDtoMapper drugOfferDtoMapper, ConsumablesOfferDtoMapper consumablesOfferDtoMapper, CompanyDtoMapper companyDtoMapper) {
        this.supplierDtoMapper = supplierDtoMapper;
        this.drugOfferDtoMapper = drugOfferDtoMapper;
        this.consumablesOfferDtoMapper = consumablesOfferDtoMapper;
        this.companyDtoMapper = companyDtoMapper;
    }

    public Offer mapToEntity(OfferDto offerDto) {
        if (offerDto == null) {
            //throw new IllegalArgumentException("OfferDto cannot be null");
            return null;
        }

        Offer offer = new Offer();
        offer.setOfferId(offerDto.getOfferId());
        offer.setOfferTitle(offerDto.getOfferTitle());
        offer.setStore(offerDto.getStore());
        offer.setCompany(offer.getCompany());

        // Handle null for offer drugs
        List<DrugsOffer> drugsOffers = Optional.ofNullable(offerDto.getDrugsOffer())
                .orElse(Collections.emptyList())
                .stream()
                .map(drugOfferDtoMapper::mapToEntity)
                .collect(Collectors.toList());
        offer.setOfferDrugs(drugsOffers);

        // Handle null for offer consumables
        List<ConsumablesOffer> consumablesOffers = Optional.ofNullable(offerDto.getConsumablesOffer())
                .orElse(Collections.emptyList())
                .stream()
                .map(consumablesOfferDtoMapper::mapToEntity)
                .collect(Collectors.toList());
        offer.setOfferConsumables(consumablesOffers);

        return offer;
    }


    public OfferDto mapToDto(Offer offer) {
        if (offer == null) {
            //throw new IllegalArgumentException("Offer cannot be null");
            return null;
        }

        OfferDto offerDto = new OfferDto();
        offerDto.setOfferId(offer.getOfferId());
        offerDto.setOfferTitle(offer.getOfferTitle());
        offerDto.setStore(offer.getStore());
        offerDto.setCompany(offer.getCompany());
        offerDto.setDrugsOffer(offer.getOfferDrugs().stream().map(drugOfferDtoMapper::mapToDto).collect(Collectors.toList()));
        offerDto.setConsumablesOffer(offer.getOfferConsumables().stream().map(consumablesOfferDtoMapper::mapToDto).collect(Collectors.toList()));

        return offerDto;
    }
}
