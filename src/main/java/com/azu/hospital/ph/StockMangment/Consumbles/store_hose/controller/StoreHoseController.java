package com.azu.hospital.ph.StockMangment.Consumbles.store_hose.controller;

import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.request.CreateStoreHoseRequest;
import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.services.StoreHoseInventorySideService;
import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.services.StoreHoseLabSideService;
import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.services.StoreHoseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/lab/store-hose")
@CrossOrigin
public class StoreHoseController {

    private final StoreHoseService storeHoseService;
    private final StoreHoseInventorySideService storeHoseInventorySideService;
    private final StoreHoseLabSideService storeHoseLabSideService;


    public StoreHoseController(
            StoreHoseService storeHoseService,
            StoreHoseInventorySideService storeHoseInventorySideService,
            StoreHoseLabSideService storeHoseLabSideService
    ) {
        this.storeHoseService = storeHoseService;
        this.storeHoseInventorySideService = storeHoseInventorySideService;
        this.storeHoseLabSideService = storeHoseLabSideService;
    }


    @PostMapping("new-order")
    public ResponseEntity<?> createStoreHoseOrder(@Valid @ModelAttribute CreateStoreHoseRequest request){
        return ResponseEntity.ok(storeHoseLabSideService.createStoreHose(request));
    }


    @PutMapping("received-order")
    public void receivedStoreHoseOrder(@RequestParam Long orderId){
        storeHoseLabSideService.receivedStoreHoseOrder(orderId);
    }

    @PutMapping("not-received-order")
    public void notReceivedStoreHoseOrder(@RequestParam Long orderId){
        storeHoseLabSideService.notReceivedStoreHoseOrder(orderId);
    }
   @PutMapping("cancel-order")
    public void cancelStoreHoseOrder(@RequestParam Long orderId){
        storeHoseLabSideService.cancelStoreHoseOrder(orderId);
    }

    @PutMapping("accept-order")
    public void acceptStoreHoseOrder(@RequestParam Long orderId , @RequestBody(required = false) String note){
        storeHoseInventorySideService.acceptStoreHoseOrder(orderId , note);
    }


    @PutMapping("reject-order")
    public void rejectStoreHoseOrder(@RequestParam Long orderId , @RequestBody String note){
        storeHoseInventorySideService.rejectStoreHoseOrder(orderId , note);
    }

   @PutMapping("prepare-order")
    public void preparingStoreHoseOrder(@RequestParam Long orderId){
        storeHoseInventorySideService.preparingStoreHoseOrder(orderId);
    }

   @PutMapping("pending-order")
    public void pendingStoreHoseOrder(@RequestParam Long orderId){
        storeHoseInventorySideService.pendingStoreHoseOrder(orderId);
    }



}
