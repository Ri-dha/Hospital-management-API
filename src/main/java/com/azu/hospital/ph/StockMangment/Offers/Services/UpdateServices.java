package com.azu.hospital.ph.StockMangment.Offers.Services;


import com.azu.hospital.exceptions.RequestValidationException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.Offers.Dao.Offer;
import com.azu.hospital.ph.StockMangment.Offers.Dao.OfferDao;
import com.azu.hospital.ph.StockMangment.Offers.Dto.OfferDto;
import com.azu.hospital.ph.StockMangment.Offers.Dto.OfferMapper;
import com.azu.hospital.ph.StockMangment.Offers.OfferUpdateRequest;
import com.azu.hospital.ph.utils.DataResponse;
import com.azu.hospital.utils.files.FileService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class UpdateServices {
    private final OfferDao offerDao;
    private final OfferMapper offerMapper;
    private final Response response;

    private final FileService fileService;

    public UpdateServices(@Qualifier("offerJpa") OfferDao offerDao, OfferMapper offerMapper, Response response, FileService fileService) {
        this.offerDao = offerDao;
        this.offerMapper = offerMapper;
        this.response = response;
        this.fileService = fileService;
    }

    public DataResponse<List<OfferDto>> updateExistOffer(Integer offerId, OfferUpdateRequest request) throws IOException {
        Offer offer = offerDao.findOfferById(offerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Supplier With id [%s] not found".formatted(offerId)
                ));
        boolean changes = false;


        if (request.offerTitle() != null && !request.offerTitle().equals(offer.getOfferTitle())) {
            offer.setOfferTitle(request.offerTitle());
            changes = true;
        }
        if (request.offerImage() != null && !request.offerImage().equals(request.offerImage())) {
            String image = fileService.saveFile(request.offerImage());
            offer.setOfferImage(image);
            changes = true;
        }
        if (request.drugsOffer() != null && !request.drugsOffer().equals(offer.getOfferDrugs())) {
            offer.setOfferDrugs(request.drugsOffer());
            changes = true;
        }
        if (request.consumablesOffer() != null && !request.consumablesOffer().equals(offer.getOfferConsumables())) {
            offer.setOfferConsumables(request.consumablesOffer());
            changes = true;
        }
        if (request.store() != null && !request.store().equals(offer.getStore())) {
            offer.setStore(request.store());
            changes = true;
        }
        if (request.company() != null && !request.company().equals(offer.getCompany())) {
            offer.setCompany(request.company());
            changes = true;
        }
        if (!changes) {
            throw new RequestValidationException("No Data changes found");
        }
        Offer updateOffer = offerDao.updateExistOffer(offer); // This line saves the changes made to the offer.
        return response.createResponse(Collections.singletonList(offerMapper.mapToDto(updateOffer)), "Offer updated successfully.", HttpStatus.OK);
    }


}
