package com.azu.hospital.ph.StockMangment.Offers.Services;


import com.azu.hospital.ph.StockMangment.Offers.Dao.Offer;
import com.azu.hospital.ph.StockMangment.Offers.Dao.OfferDao;
import com.azu.hospital.ph.StockMangment.Offers.Dto.OfferDto;
import com.azu.hospital.ph.StockMangment.Offers.Dto.OfferMapper;
import com.azu.hospital.ph.utils.DataResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServices {

    private final OfferDao offerDao;
    private final OfferMapper offerMapper;
    private final Response response;

    public OfferServices(@Qualifier("offerJpa") OfferDao offerDao, OfferMapper offerMapper, Response response) {
        this.offerDao = offerDao;
        this.offerMapper = offerMapper;
        this.response = response;
    }

    public DataResponse<List<OfferDto>> getMostSearchedOffers(Pageable pageable) {
        List<Offer> offers = offerDao.findMostSearched(pageable);
        List<OfferDto> offerDtos = offers.stream().map(offerMapper::mapToDto).collect(Collectors.toList());

        Page<Offer> offerPage = new PageImpl<>(offers, pageable, offers.size());
        return response.createResponse(offerDtos, "Most searched offers retrieved successfully.", HttpStatus.OK, offerPage);
    }

    public DataResponse<List<OfferDto>> getMostFavoredOffers(Pageable pageable) {
        List<Offer> offers = offerDao.findMostFavored(pageable);
        List<OfferDto> offerDtos = offers.stream().map(offerMapper::mapToDto).collect(Collectors.toList());

        Page<Offer> offerPage = new PageImpl<>(offers, pageable, offers.size());
        return response.createResponse(offerDtos, "Most favored offers retrieved successfully.", HttpStatus.OK, offerPage);
    }

}
