package com.azu.hospital.ph.StockMangment.Offers;


import com.azu.hospital.ph.StockMangment.Offers.Dto.OfferDto;
import com.azu.hospital.ph.StockMangment.Offers.Services.AddService;
import com.azu.hospital.ph.StockMangment.Offers.Services.OfferService;
import com.azu.hospital.ph.StockMangment.Offers.Services.OfferServices;
import com.azu.hospital.ph.StockMangment.Offers.Services.UpdateServices;
import com.azu.hospital.ph.utils.DataResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/offers")
@CrossOrigin
public class OfferController {

    private final OfferService offerService;
    private final OfferServices offerServices;
    private final AddService addService;
    private final UpdateServices updateServices;

    public OfferController(OfferService offerService, OfferServices offerServices, AddService addService, UpdateServices updateServices) {
        this.offerService = offerService;
        this.offerServices = offerServices;
        this.addService = addService;
        this.updateServices = updateServices;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<Page<OfferDto>> getAllOffers(Pageable pageable) {
        offerService.getAllOffers(pageable);
        return ResponseEntity.ok(offerService.getAllOffers(pageable));
    }
    @PostMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public void addNewOffer(
           @Valid @ModelAttribute OfferRegistrationRequest registrationRequest
    ) throws IOException {
        addService.addNewOffer(registrationRequest);
    }


    @PutMapping("/{offerId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<DataResponse<List<OfferDto>>> updateOffer(@PathVariable Integer offerId, @RequestBody OfferUpdateRequest request) throws IOException {
        DataResponse<List<OfferDto>> response = updateServices.updateExistOffer(offerId, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{offerId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<DataResponse<Boolean>> deleteOffer(@PathVariable Integer offerId) {

        DataResponse<Boolean> response = offerService.deleteOffer(offerId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{offerId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<DataResponse<List<OfferDto>>> findOfferById(@PathVariable Integer offerId) {
        DataResponse<List<OfferDto>> response = offerService.findOfferById(offerId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public DataResponse<List<OfferDto>> findOffers(
            @RequestParam(required = false) String druOfferTradeName,
            @RequestParam(required = false) String druOfferScientificName,
            @RequestParam(required = false) String consumableOfferTradeName,
            @RequestParam(required = false) String drugOfferBarcode,
            @RequestParam(required = false) String consumableOfferBarcode,
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String supplierName,
            Pageable pageable) {
        return offerService.findOffers(druOfferTradeName, druOfferScientificName, consumableOfferTradeName,
                drugOfferBarcode, consumableOfferBarcode, companyName,
                supplierName, pageable);
    }

    @GetMapping("/offers/most-searched")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public DataResponse<List<OfferDto>> getMostSearchedOffers(Pageable pageable) {
        return offerServices.getMostSearchedOffers(pageable);
    }

    @GetMapping("/offers/most-favored")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public DataResponse<List<OfferDto>> getMostFavoredOffers(Pageable pageable) {
        return offerServices.getMostFavoredOffers(pageable);
    }

}
