package com.azu.hospital.ph.StockMangment.Offers.list;

import lombok.Data;

import java.time.LocalDate;
@Data
public class DrugOfferDto {
    private Integer druOfferId;
    private String druOfferTradeName;
    private String druOfferScientificName;
    private String drugOfferBarcode;
    private String druOfferDescriptions;
    private LocalDate druOfferExpDate;
    private String druOfferImageUrl;
}
