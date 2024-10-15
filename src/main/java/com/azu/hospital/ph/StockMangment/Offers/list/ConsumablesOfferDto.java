package com.azu.hospital.ph.StockMangment.Offers.list;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ConsumablesOfferDto {
    private Integer consumableOfferId;
    private String consumableOfferTradeName;
    private String consumableOfferBarcode;
    private String consumableOfferDescriptions;
    private LocalDate consumableOfferExpDate;
    private String consumableOfferImageUrl;
}
