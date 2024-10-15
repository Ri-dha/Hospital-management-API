package com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable;

import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.service.ConsumablesRequestTableListService;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.service.ConsumablesRequestTableListStatusRequest;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.request.ConsumablesRequestTableRequest;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.request.ConsumablesRequestTableUpdateRequest;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.services.*;
import com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.requests.ItemRejectStateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consumables-requests-handler")
@CrossOrigin
public class ConsumablesRequestController {

    private final ConsumablesRequestAddServices addServices;
    private final ConsumablesRequestPreparingServices preparingServices;
    private final ConsumablesRequestStatusServices statusServices;
    private final ConsumablesRequestUpdateServices updateServices;

    private final ConsumablesRequestGetServices getServices;

    private final ConsumablesRequestTableListService consumablesRequestTableListService;

    @Autowired
    public ConsumablesRequestController(ConsumablesRequestAddServices addServices,
                                        ConsumablesRequestPreparingServices preparingServices,
                                        ConsumablesRequestStatusServices statusServices, ConsumablesRequestUpdateServices updateServices, ConsumablesRequestGetServices getServices, ConsumablesRequestTableListService consumablesRequestTableListService) {
        this.addServices = addServices;
        this.preparingServices = preparingServices;
        this.statusServices = statusServices;
        this.updateServices = updateServices;
        this.getServices = getServices;
        this.consumablesRequestTableListService = consumablesRequestTableListService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<Void> addNewRequest(
            @RequestBody List<ConsumablesRequestTableRequest> request,
            @RequestParam(required = false) String note
            ) {
        addServices.addNewRequests(request, note);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/prepare/{requestId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<ObjectNode> prepareItemRequest(@PathVariable Long requestId, @RequestParam String status) {
        return ResponseEntity.ok(preparingServices.preparingTheItemRequest(requestId, status));
    }

    @PutMapping("/done/{requestId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<ObjectNode> doneItemRequest(
            @PathVariable Long requestId, @RequestParam String status
    ) {
        return ResponseEntity.ok(preparingServices.doneTheItemRequest(requestId, status));
    }

    @PutMapping("/accept-reject/")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<ObjectNode> acceptOrRejectRequest(@RequestParam Long requestId,
                                                            @RequestBody List<ConsumablesRequestTableListStatusRequest> request) {
        return ResponseEntity.ok(consumablesRequestTableListService.acceptOrRejectRequest(requestId, request));
    }

    @PutMapping("/cancel/{requestId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<ObjectNode> cancelRequest(@PathVariable Long requestId, @RequestParam String status) {
        return ResponseEntity.ok(statusServices.cancelRequest(requestId, status));
    }

    @PutMapping("/received/{requestId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<ObjectNode> receivedOrNotReceivedItemRequest(@PathVariable Long requestId, @RequestParam String status) {
        return ResponseEntity.ok(statusServices.receivedOrNotReceivedTheItemRequest(requestId, status));
    }

    @PutMapping("/all-item-request-params/{requestId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<Void> updateItemRequestParams(@PathVariable Long requestId,
                                                        @RequestBody ConsumablesRequestTableUpdateRequest request) {
        updateServices.updateRequestConsumable(requestId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<?> getAllRequests(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "10", name = "size") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(getServices.getAllList(pageable));
    }


    @GetMapping("/all-by-ward-or-unit")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<?> getAllByWardOrUnit(
            @RequestParam(required = false) Long wardId,
            @RequestParam(required = false) Long unitId,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "10", name = "size") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(consumablesRequestTableListService.getAllByWardOrUnit(wardId, unitId, pageable));
    }
}
