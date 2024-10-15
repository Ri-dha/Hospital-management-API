package com.azu.hospital.ph.StockMangment.entringItemList;

import com.azu.hospital.ph.utils.data.AbstractApiResponse;
import org.springframework.http.HttpStatusCode;

public class MathApiResponse extends AbstractApiResponse  {

    public MathApiResponse(String message, boolean status, int statusCode, Object data) {
        super(message, status, statusCode, data);
    }

    public MathApiResponse() {
    }

    @Override
    public boolean getStatus() {
        return true;
    }
}
