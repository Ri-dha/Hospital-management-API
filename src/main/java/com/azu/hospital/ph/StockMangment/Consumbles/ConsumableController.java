package com.azu.hospital.ph.StockMangment.Consumbles;


import com.azu.hospital.ph.StockMangment.Consumbles.Dto.ConsumableDto;
import com.azu.hospital.ph.StockMangment.Consumbles.Dto.ConsumableDtoSpecial;
import com.azu.hospital.ph.StockMangment.Consumbles.services.AddConsumablesService;
import com.azu.hospital.ph.StockMangment.Consumbles.services.ConsumableServices;
import com.azu.hospital.ph.StockMangment.Consumbles.services.ConsumablesUpdateService;
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

@RestController
@RequestMapping(path = "api/v1/consumables")
@CrossOrigin
public class ConsumableController {
    private final ConsumableServices consumableServices;
    private final AddConsumablesService addConsumablesService;
    private final ConsumablesUpdateService updateService;

    @Autowired
    public ConsumableController(ConsumableServices consumableServices, AddConsumablesService addConsumablesService, ConsumablesUpdateService updateService) {
        this.consumableServices = consumableServices;
        this.addConsumablesService = addConsumablesService;
        this.updateService = updateService;
    }



    @GetMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK', 'NURSES')")
    public ResponseEntity<?> findByDrugSortedBOrderBy(
            @RequestParam(required = false, defaultValue = "") String consumableName,
            @RequestParam(required = false, defaultValue = "") String barcode,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "10", name = "size") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ConsumableDto> dto = consumableServices.findByDrugSortedBOrderBy(consumableName, barcode, pageable );
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/all-only-names")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK', 'NURSES')")
    public ResponseEntity<?> findSortedBOrderBy(
            @RequestParam(required = false, defaultValue = "") String consumableName,
            @RequestParam(required = false, defaultValue = "") String barcode,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "10", name = "size") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ConsumableDtoSpecial> dto = consumableServices.findConsumablesBySortedBOrderBy(consumableName, barcode, pageable );
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{consumableId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ConsumableDto getConsumablesByIds(@PathVariable Long consumableId){
        return consumableServices.getConsumableById(consumableId);
    }

    @GetMapping("/get-by-barcode")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ConsumableDto getConsumablesByBarcode(
            @RequestParam String barcode){
        return consumableServices.getConsumableByBarcode(barcode);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    @ResponseStatus(HttpStatus.OK)
    public void createConsumable(@Valid
            @ModelAttribute ConsumableRegistrationRequest request,
             @RequestParam(name = "billId") Long billId
    ) throws IOException {
         addConsumablesService.registerConsumable(request, billId);
    }


    @DeleteMapping("/{consumableId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public void deleteConsumableByIds(@PathVariable Long consumableId){
        consumableServices.deleteConsumableBYId(consumableId);
    }

    @PutMapping("{consumableId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public void updateConsumable(
            @PathVariable("consumableId") Long consumableId,
            @ModelAttribute ConsumableUpdateRequest updateRequest
    ) throws IOException {
        updateService.consumableUpdate(consumableId,updateRequest);
    }

}
