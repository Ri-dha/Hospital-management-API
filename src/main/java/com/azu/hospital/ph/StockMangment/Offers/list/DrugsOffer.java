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
@AllArgsConstructor
@Data
@Entity
public class DrugsOffer extends BaseSearchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer druOfferId;
    private String druOfferTradeName;
    private String druOfferScientificName;
    private String drugOfferBarcode;
    private String druOfferDescriptions;
    private LocalDate druOfferExpDate;
    private String druOfferImageUrl;

    public DrugsOffer() {
    }

}
