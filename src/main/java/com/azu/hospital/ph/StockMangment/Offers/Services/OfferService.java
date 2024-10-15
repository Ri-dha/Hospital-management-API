package com.azu.hospital.ph.StockMangment.Offers.Services;

import com.azu.hospital.utils.image.ImageRepository;
import com.azu.hospital.ph.StockMangment.Company.CompanyDao;

import com.azu.hospital.ph.StockMangment.Offers.Dao.Offer;
import com.azu.hospital.ph.StockMangment.Offers.Dao.OfferDao;
import com.azu.hospital.ph.StockMangment.Offers.Dto.OfferDto;
import com.azu.hospital.ph.StockMangment.Offers.Dto.OfferMapper;
import com.azu.hospital.ph.utils.DataResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class OfferService {
    private final OfferDao offerDao;

    private final OfferMapper offerMapper;
    private final ImageRepository imageRepository;
    private final CompanyDao companyDao;
    private final Response response;


    public OfferService(OfferDao offerDao, OfferMapper offerMapper, ImageRepository imageRepository, CompanyDao companyDao, Response response) {
        this.offerDao = offerDao;
        this.offerMapper = offerMapper;
        this.imageRepository = imageRepository;
        this.companyDao = companyDao;
        this.response = response;
    }

    public Page<OfferDto> getAllOffers(Pageable pageable) {
        Page<Offer> offerPage = offerDao.getAllOffers(pageable);
        List<OfferDto> offerDtoList = offerPage.getContent()
                .stream()
                .map(offerMapper::mapToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(offerDtoList, pageable, offerPage.getTotalElements());
    }




    public DataResponse<List<OfferDto>> findOffers(String druOfferTradeName, String druOfferScientificName,
                                                   String consumableOfferTradeName, String drugOfferBarcode,
                                                   String consumableOfferBarcode, String companyName,
                                                   String supplierName, Pageable pageable) {
        List<Offer> offers = offerDao.findOffers(druOfferTradeName, druOfferScientificName, consumableOfferTradeName,
                drugOfferBarcode, consumableOfferBarcode, companyName,
                supplierName, pageable);
        List<OfferDto> offerDtos = offers.stream()
                .map(offerMapper::mapToDto)
                .collect(Collectors.toList());

        Page<Offer> offerPage = new PageImpl<>(offers, pageable, offers.size());

        return response.createResponse(offerDtos, "Offers retrieved successfully.", HttpStatus.OK, offerPage);
    }



    public DataResponse<List<OfferDto>> findOfferById(Integer offerId) {
        Offer offer = offerDao.findOfferById(offerId).orElseThrow(() -> new EntityNotFoundException("Offer not found with ID: " + offerId));
        return response.createResponse(Collections.singletonList(offerMapper.mapToDto(offer)), "Offer found successfully.", HttpStatus.OK);
    }


    public DataResponse<Boolean> deleteOffer(Integer offerId) {
        try {
            offerDao.deleteOffer(offerId);
            return response.createResponse(Collections.emptyList(), "Offer with id: " + offerId + " deleted successfully", HttpStatus.OK, true);
        } catch (EntityNotFoundException e) {
            return response.createResponse(Collections.emptyList(), "Offer with id: " + offerId + " not found", HttpStatus.NOT_FOUND, false);
        }
    }


}
