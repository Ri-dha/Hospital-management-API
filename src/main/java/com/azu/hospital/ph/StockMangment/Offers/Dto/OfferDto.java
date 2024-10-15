package com.azu.hospital.ph.StockMangment.Offers.Dto;


import com.azu.hospital.ph.StockMangment.Company.CompanyDto;
import com.azu.hospital.ph.StockMangment.Consumbles.Dto.ConsumableDto;
import com.azu.hospital.ph.StockMangment.Offers.list.ConsumablesOfferDto;
import com.azu.hospital.ph.StockMangment.Offers.list.DrugOfferDto;
import com.azu.hospital.ph.StockMangment.Supplier.SupplierDto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter
public class OfferDto {

    private Integer offerId;
    private String offerTitle;
    private String store;
    private String company;
    private List<DrugOfferDto> drugsOffer;
    private List<ConsumablesOfferDto> consumablesOffer;

}
