package com.azu.hospital.ph.StockMangment.Offers.Services;


import com.azu.hospital.ph.StockMangment.Offers.Dao.Offer;
import com.azu.hospital.ph.StockMangment.Offers.Dto.OfferDto;
import com.azu.hospital.ph.utils.DataResponse;
import com.azu.hospital.ph.utils.PaginationInfo;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Response {


    public DataResponse<List<OfferDto>> createResponse(List<OfferDto> data, String message, HttpStatus status) {
        return createResponse(data, message, status, null);
    }

    public DataResponse<List<OfferDto>> createResponse(List<OfferDto> data, String message, HttpStatus status, Page<Offer> offerPage) {
        DataResponse<List<OfferDto>> response = new DataResponse<>();
        response.setData(data);
        response.setMessage(message);
        response.setStatus(status.value());

        if (offerPage != null) {
            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.getPaginationInfo(offerPage);
            response.setPaginationInfo(paginationInfo);
        }

        return response;
    }
    public DataResponse<Boolean> createResponse(List<OfferDto> data, String message, HttpStatus status, boolean deletionStatus) {
        DataResponse<Boolean> response = new DataResponse<>();
        response.setData(deletionStatus);
        response.setMessage(message);
        response.setStatus(status.value());
        return response;
    }

}
