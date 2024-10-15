package com.azu.hospital.ph.Sales.controller;


import com.azu.hospital.ph.Sales.customer.request.CustomerUpdateRequest;
import com.azu.hospital.ph.Sales.Request.SoldBillRegistrationRequest;
import com.azu.hospital.ph.Sales.Request.SoldBillUpdateRequest;
import com.azu.hospital.ph.Sales.solid_item_list.request.SoldItemsUpdateRequest;
import com.azu.hospital.ph.Sales.services.SoldBillService;
import com.azu.hospital.ph.Sales.services.SoldBillServicesGetter;
import com.azu.hospital.ph.Sales.services.UpdateBillServices;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/sold_bill")
@CrossOrigin

public class SoldBillController {

    private final SoldBillService soldBillService;
    private final UpdateBillServices updateBillServices;
    private final SoldBillServicesGetter getter;

    @Autowired
    public SoldBillController(SoldBillService soldBillService, UpdateBillServices updateBillServices, SoldBillServicesGetter getter) {
        this.soldBillService = soldBillService;
        this.updateBillServices = updateBillServices;
        this.getter = getter;
    }

    @PostMapping
    public ResponseEntity<Map<String, Long>> createNewBill(
            @RequestBody SoldBillRegistrationRequest request
    ) {
        return ResponseEntity.ok(soldBillService.createNewSoldBill(request));
    }


    @GetMapping("/all-sales-list")
    public ResponseEntity<?> getAllBills(
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "10") int size,
            @RequestParam(name = "startDate",required = false) LocalDate startDate,
            @RequestParam(name = "endDate",required = false) LocalDate endDate
    ) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(getter.getAllBills(startDate, endDate, pageable));
    }



    @GetMapping("/{billId}")
    public ResponseEntity<?> getBillById(@PathVariable Long billId) {
        return ResponseEntity.ok( getter.getBillById(billId));
    }


    @GetMapping("/most-sold")
    public ResponseEntity<?> getMostSoldItems(
            @RequestParam(value = "top", defaultValue = "10") int topN
    ) {
        List<SoldBillServicesGetter.ItemSummary> mostSoldItems = getter.findMostSoldItems(topN);
        return ResponseEntity.ok(mostSoldItems);
    }



    @PutMapping("/bill")
    public ResponseEntity<Void> updateBill(@Valid
            @RequestParam("billId")  Long billId,
            @RequestBody() SoldBillUpdateRequest update
    ) {
        updateBillServices.solidBillUpdate(billId,update);
        return ResponseEntity.ok().build();
    }

    // TODO: 12/24/2023 this folder missing pharmacists auth and pharmacist name
    // TODO: 12/24/2023 link with drugs table and consumable table for minimising in each table exist and expanse
    // TODO: 12/24/2023 recheck for any thing else
    // TODO: 12/24/2023 search by patient name




}
