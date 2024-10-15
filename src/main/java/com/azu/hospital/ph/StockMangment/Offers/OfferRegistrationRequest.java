package com.azu.hospital.ph.StockMangment.Offers;

import com.azu.hospital.ph.StockMangment.Company.Company;
import com.azu.hospital.ph.StockMangment.Offers.list.ConsumablesOffer;
import com.azu.hospital.ph.StockMangment.Offers.list.ConsumablesOfferRequest;
import com.azu.hospital.ph.StockMangment.Offers.list.DrugsOffer;
import com.azu.hospital.ph.StockMangment.Supplier.Supplier;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class OfferRegistrationRequest {


    private String offerTitle;
    private MultipartFile offerImage;
    private List<DrugsOffer> drugsOffer;
    private List<ConsumablesOfferRequest> consumablesOffer;
    private String store;
    private String company;


    public OfferRegistrationRequest(
            String offerTitle
    ) {
        this.offerTitle = offerTitle;
    }


    public OfferRegistrationRequest(
            String offerTitle,
            MultipartFile offerImage,
            List<DrugsOffer> drugsOffer,
            List<ConsumablesOfferRequest> consumablesOffer,
            String store,
            String company
    ) {
        this.offerTitle = offerTitle;
        this.offerImage = offerImage;
        this.drugsOffer = drugsOffer;
        this.consumablesOffer = consumablesOffer;
        this.store = store;
        this.company = company;
    }

    public OfferRegistrationRequest() {
    }
}

