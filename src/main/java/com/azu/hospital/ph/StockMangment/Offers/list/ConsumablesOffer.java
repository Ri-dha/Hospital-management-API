package com.azu.hospital.ph.StockMangment.Offers.list;


import com.azu.hospital.ph.utils.favor.BaseSearchEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Entity
public class ConsumablesOffer extends BaseSearchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer consumableOfferId;
    private String consumableOfferTradeName;
    private String consumableOfferBarcode;
    private String consumableOfferDescriptions;
    private LocalDate consumableOfferExpDate;
    private String consumableOfferImageUrl;


    public ConsumablesOffer() {
    }
}


