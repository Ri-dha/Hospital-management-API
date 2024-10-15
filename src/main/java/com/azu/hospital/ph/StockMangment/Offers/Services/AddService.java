package com.azu.hospital.ph.StockMangment.Offers.Services;


import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.Company.Company;
import com.azu.hospital.ph.StockMangment.Company.CompanyDao;
import com.azu.hospital.ph.StockMangment.Offers.Dao.Offer;
import com.azu.hospital.ph.StockMangment.Offers.Dao.OfferDao;
import com.azu.hospital.ph.StockMangment.Offers.Dto.OfferMapper;
import com.azu.hospital.ph.StockMangment.Offers.OfferRegistrationRequest;
import com.azu.hospital.ph.StockMangment.Offers.list.*;
import com.azu.hospital.ph.StockMangment.Supplier.Supplier;
import com.azu.hospital.ph.StockMangment.Supplier.SupplierDao;
import com.azu.hospital.utils.files.FileService;
import com.azu.hospital.utils.image.ImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddService {
    private final OfferDao offerDao;
    private final OfferMapper offerMapper;
    private final CompanyDao companyDao;
    private final ImageRepository imageRepository;
    private final Response response;
    private final SupplierDao supplierDao;
    private final DrugsOfferRepository drugsOfferRepository;
    private final ConsumableOfferRepository consumableOfferRepository;

    private final FileService fileService;

    public AddService(OfferDao offerDao, OfferMapper offerMapper, CompanyDao companyDao, ImageRepository imageRepository, Response response, SupplierDao supplierDao, DrugsOfferRepository drugsOfferRepository, ConsumableOfferRepository consumableOfferRepository, FileService fileService) {
        this.offerDao = offerDao;
        this.offerMapper = offerMapper;
        this.companyDao = companyDao;
        this.imageRepository = imageRepository;
        this.response = response;
        this.supplierDao = supplierDao;
        this.drugsOfferRepository = drugsOfferRepository;
        this.consumableOfferRepository = consumableOfferRepository;
        this.fileService = fileService;
    }

    @Transactional
    public void addNewOffer(OfferRegistrationRequest registrationRequest) throws IOException {
        Offer offer1 = new Offer(
                registrationRequest.getOfferTitle()
        );
        offer1.setOfferTitle(registrationRequest.getOfferTitle());

        if (registrationRequest.getOfferImage() != null) {
            String image = fileService.saveFile(registrationRequest.getOfferImage());
            offer1.setOfferImage(image);
        }


        offer1.setCompany(registrationRequest.getCompany());
        offer1.setStore(registrationRequest.getStore());


        List<DrugsOffer> savedDrugsOffers = new ArrayList<>();
        if (registrationRequest.getDrugsOffer() != null) {
            List<DrugsOffer> drugsOffer = registrationRequest.getDrugsOffer();
            savedDrugsOffers = drugsOffer.stream().map(drugsOffer1 -> {
                        DrugsOffer drugsOffer2 = new DrugsOffer();
                        drugsOffer2.setDruOfferTradeName(drugsOffer1.getDruOfferTradeName());
                        drugsOffer2.setDruOfferScientificName(drugsOffer1.getDruOfferScientificName());
                        drugsOffer2.setDrugOfferBarcode(drugsOffer1.getDrugOfferBarcode());
                        drugsOffer2.setDruOfferDescriptions(drugsOffer1.getDruOfferDescriptions());
                        drugsOffer2.setDruOfferExpDate(drugsOffer1.getDruOfferExpDate());
                        drugsOffer2.setDruOfferImageUrl(drugsOffer1.getDruOfferImageUrl());

                        drugsOfferRepository.save(drugsOffer2);
                        return drugsOffer2;
                    }
            ).collect(Collectors.toList());
        }

        offer1.setOfferDrugs(savedDrugsOffers);

        List<ConsumablesOffer> savedConsumablesOffers = new ArrayList<>();
        List<ConsumablesOfferRequest> consumablesOfferList = registrationRequest.getConsumablesOffer();
        if (consumablesOfferList != null) {
            savedConsumablesOffers = consumablesOfferList.stream().map(consumablesOffer -> {
                ConsumablesOffer consumablesOffer1 = new ConsumablesOffer();
                consumablesOffer1.setConsumableOfferTradeName(consumablesOffer.getConsumableOfferTradeName());
                consumablesOffer1.setConsumableOfferBarcode(consumablesOffer.getConsumableOfferBarcode());
                consumablesOffer1.setConsumableOfferDescriptions(consumablesOffer.getConsumableOfferDescriptions());
                consumablesOffer1.setConsumableOfferExpDate(consumablesOffer.getConsumableOfferExpDate());

                if (consumablesOffer.getConsumableOfferExpDate() != null) {
                    try {
                        String image = fileService.saveFile(consumablesOffer.getConsumableOfferImageUrl());
                        consumablesOffer1.setConsumableOfferImageUrl(image);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
                consumableOfferRepository.save(consumablesOffer1);
                return consumablesOffer1;

            }).collect(Collectors.toList());
        }

        offer1.setOfferConsumables(savedConsumablesOffers);

        offerDao.addNewOffer(offer1);

    }

}
