package com.azu.hospital.ph.StockMangment.Offers.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class ConsumablesOfferRequest {

    private String consumableOfferTradeName;

    private String consumableOfferBarcode;

    private String consumableOfferDescriptions;

    private LocalDate consumableOfferExpDate;

    private MultipartFile consumableOfferImageUrl;

    public ConsumablesOfferRequest(String consumableOfferTradeName, String consumableOfferBarcode, String consumableOfferDescriptions, LocalDate consumableOfferExpDate, MultipartFile consumableOfferImageUrl) {
        this.consumableOfferTradeName = consumableOfferTradeName;
        this.consumableOfferBarcode = consumableOfferBarcode;
        this.consumableOfferDescriptions = consumableOfferDescriptions;
        this.consumableOfferExpDate = consumableOfferExpDate;
        this.consumableOfferImageUrl = consumableOfferImageUrl;
    }

    public ConsumablesOfferRequest() {
    }
}
