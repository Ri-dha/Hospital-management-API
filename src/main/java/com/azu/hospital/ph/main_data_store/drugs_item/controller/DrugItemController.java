package com.azu.hospital.ph.main_data_store.drugs_item.controller;

import com.azu.hospital.Validator.DateValidator.DatePattern;
import com.azu.hospital.ph.main_data_store.drugs_item.dto.DrugIsBackDto;
import com.azu.hospital.ph.main_data_store.drugs_item.dto.DrugItemDto;
import com.azu.hospital.ph.main_data_store.drugs_item.request.DrugItemRegistrationRequest;
import com.azu.hospital.ph.main_data_store.drugs_item.services.DrugItemAddServices;
import com.azu.hospital.ph.main_data_store.drugs_item.services.DrugItemGetServices;
import com.azu.hospital.ph.main_data_store.drugs_item.services.DrugItemUpdateServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("api/v1/drugs-item")
@CrossOrigin
public class DrugItemController {

    private final DrugItemAddServices addServices;
    private final DrugItemGetServices getServices;
    private final DrugItemUpdateServices updateServices;

    @Autowired
    public DrugItemController(DrugItemAddServices addServices, DrugItemGetServices getServices, DrugItemUpdateServices updateServices) {
        this.addServices = addServices;
        this.getServices = getServices;
        this.updateServices = updateServices;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'INVENTORY_CLERK')")
    public ResponseEntity<Void> addNewDrug(@Valid
                                           @RequestParam("billId") Long billId,
                                           @ModelAttribute DrugItemRegistrationRequest request) throws IOException {
        addServices.addNewDrugItem(billId, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/add-by-barcode")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'INVENTORY_CLERK')")
    public ResponseEntity<Void> addByBarcode(
            @RequestParam("barcode") String barcode,
            @RequestParam("quantity") Integer quantity,
            @RequestParam("isBack") Boolean isBack
    ){
        addServices.addDrugByBarcode(barcode, quantity, isBack);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'INVENTORY_CLERK')")
    public ResponseEntity<DrugItemDto> getDrugItemByBarcode(@PathVariable Long id){
        return ResponseEntity.ok(getServices.getById(id));
    }

    @GetMapping("/get-by-barcode")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'INVENTORY_CLERK')")
    public ResponseEntity<DrugItemDto> getDrugItemByBarcode(@RequestParam("barcode") String barcode){
        return ResponseEntity.ok(getServices.getByBarcode(barcode));
    }

    @GetMapping("/all-with-filter")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'INVENTORY_CLERK')")
    public ResponseEntity<Page<DrugItemDto>> getAllItemWithFilter(
            @RequestParam(value = "drugId", required = false) Long drugId,
            @RequestParam(value = "drugTradeName", required = false) String drugTradeName,
            @RequestParam(value = "drugScientificName", required = false) String drugScientificName,
            @RequestParam(value = "barcode", required = false) String barcode,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(getServices.getAllDrugs(drugId, drugTradeName, drugScientificName, barcode, pageable));
    }

    @GetMapping("/get-all-expired-drugs")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'INVENTORY_CLERK')")
    public ResponseEntity<Page<DrugItemDto>> getAllExpireDrugs(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(getServices.getAllExpireDrugs( pageable));
    }

    @GetMapping("/get-all-expired-drugs-special")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'INVENTORY_CLERK')")
    public ResponseEntity<Page<DrugItemDto>> getAllExpireDrugsSpecial(
            @RequestParam(value = "expDate", required = false)@DatePattern String expDate,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(getServices.getAllExpireDrugsSpecial(expDate, pageable));
    }

    @GetMapping("/get-all-refund-drugs")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'INVENTORY_CLERK')")
    public ResponseEntity<Page<DrugIsBackDto>> getAllRefundDrugs(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(getServices.getAllRefundDrugs( pageable));
    }

    @PutMapping("/{drugId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'INVENTORY_CLERK')")
    @ResponseStatus(HttpStatus.OK)
    public void updateDrug(@Valid
                           @PathVariable("drugId") Long drugId,
                           @ModelAttribute DrugItemRegistrationRequest updateRequest
    ) throws IOException {
        updateServices.updateDrugItem(drugId, updateRequest);
    }

    @GetMapping("/get-all-drugs-with-price")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'INVENTORY_CLERK')")
    public ResponseEntity<Page<DrugItemDto>> getAllDrugsWithPrice(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(getServices.getAllDrugsWithPrice(pageable));
    }

}
