package com.azu.hospital.ph.StockMangment.addOtherItems;


import com.azu.hospital.ph.StockMangment.addOtherItems.dto.OtherItemDto;
import com.azu.hospital.ph.StockMangment.addOtherItems.request.OtherItemRegistrationRequest;
import com.azu.hospital.ph.StockMangment.addOtherItems.request.OtherItemUpdateRequest;
import com.azu.hospital.ph.StockMangment.addOtherItems.sevice.OtherItemServices;
import com.azu.hospital.ph.StockMangment.addOtherItems.sevice.UpdateService;
import com.azu.hospital.utils.enums.DeviceType;
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
@RequestMapping("api/v1/other_item")
@CrossOrigin
public class OtherItemController {
    private final OtherItemServices otherItemServices;
    private final UpdateService updateService;

    @Autowired
    public OtherItemController(OtherItemServices otherItemServices, UpdateService updateService) {
        this.otherItemServices = otherItemServices;
        this.updateService = updateService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewItems(
            @Valid @RequestParam() Long billId,
            @ModelAttribute OtherItemRegistrationRequest registrationRequest) throws IOException {
         otherItemServices.addNewItem(billId, registrationRequest);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<?> selectAllItems(
            @RequestParam(required = false) String itemName,
            @RequestParam(required = false) String itemCompany,
            @RequestParam(required = false) String itemBarcode,
            @RequestParam(required = false) DeviceType type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){

        Pageable pageable = PageRequest.of(page, size);
       Page<OtherItemDto> itemDtoPage = otherItemServices.getAllItems( itemName, itemCompany, itemBarcode,type, pageable);
       return ResponseEntity.ok(itemDtoPage);
    }

    @GetMapping ("/{itemId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    @ResponseStatus(HttpStatus.OK)
    public OtherItemDto getItem(
            @PathVariable("itemId") Long itemId
    ) {
        return otherItemServices.getItemById(itemId);
    }

    @PutMapping(value = "/{itemId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public void updateItem(@PathVariable("itemId") Long itemId,
                            @ModelAttribute OtherItemUpdateRequest updateRequest) throws IOException {
         updateService.updateItem(itemId, updateRequest);
    }

    @DeleteMapping("/{itemId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable("itemId") Long itemId){
        otherItemServices.deleteItemById(itemId);
    }
}
