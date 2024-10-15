package com.azu.hospital.ph.StockMangment.Offers;

import com.azu.hospital.utils.image.Image;
import com.azu.hospital.ph.StockMangment.Company.Company;

import com.azu.hospital.ph.StockMangment.Offers.list.ConsumablesOffer;
import com.azu.hospital.ph.StockMangment.Offers.list.DrugsOffer;
import com.azu.hospital.ph.StockMangment.Supplier.Supplier;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public record OfferUpdateRequest(
        String offerTitle,
        MultipartFile offerImage,
        List<DrugsOffer> drugsOffer,
        List<ConsumablesOffer> consumablesOffer,
        String store,
        String company
)
{

}
