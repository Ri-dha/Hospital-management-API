package com.azu.hospital.ph.Sales.solid_item_list.controller;

import com.azu.hospital.ph.Sales.solid_item_list.dto.SoldItemsDto;
import com.azu.hospital.ph.Sales.solid_item_list.request.SoldItemsUpdateRequest;
import com.azu.hospital.ph.Sales.solid_item_list.services.SoldItemAddServices;
import com.azu.hospital.ph.Sales.solid_item_list.services.SoldItemDeleteServices;
import com.azu.hospital.ph.Sales.solid_item_list.services.SoldItemGetServices;
import com.azu.hospital.ph.Sales.solid_item_list.services.SoldItemUpdateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sold-item-list")
@CrossOrigin
public class SoldItemController {

    private final SoldItemAddServices addServices;
    private final SoldItemGetServices getServices;
    private final SoldItemUpdateServices updateServices;
    private final SoldItemDeleteServices deleteServices;

    @Autowired
    public SoldItemController(SoldItemAddServices addServices,
                              SoldItemGetServices getServices,
                              SoldItemUpdateServices updateServices,
                              SoldItemDeleteServices deleteServices) {
        this.addServices = addServices;
        this.getServices = getServices;
        this.updateServices = updateServices;
        this.deleteServices = deleteServices;
    }

    @PostMapping("/add-item-list")
    public ResponseEntity<Void> createNewItemList(
            @RequestParam("billId") Long billId,
            @RequestParam("requestId") Long requestId,
            @RequestBody List<SoldItemsUpdateRequest> request
    ) {
        addServices.addItemList(billId,requestId,request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-item-list")
    public ResponseEntity<Void> updateExistItem(
            @RequestParam("itemId") Long itemId,
            @RequestBody SoldItemsUpdateRequest request
    ){
        updateServices.updateItemList(itemId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoldItemsDto> getSoldItemsById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(getServices.getItemById(id));
    }

    @GetMapping("/get-all-items")
    public ResponseEntity<Page<SoldItemsDto>> getSoldItemsById(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(getServices.getAllItems(pageable));
    }

    @GetMapping("/get-all-items-by-bill-id")
    public ResponseEntity<List<SoldItemsDto>> getAllSoldItemsById(
            @RequestParam(value = "id") Long id){
        return ResponseEntity.ok(getServices.getAllItemsByListId(id));
    }

    @DeleteMapping("/delete-item-by-id")
    public ResponseEntity<Void> deleteItem(@RequestParam("id") Long id){
        deleteServices.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
