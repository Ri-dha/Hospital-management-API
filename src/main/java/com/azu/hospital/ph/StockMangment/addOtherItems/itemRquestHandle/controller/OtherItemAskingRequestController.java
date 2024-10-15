package com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.controller;

import com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.dto.OtherItemAskingRequestDto;
import com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.requests.AddRequestRequest;
import com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.requests.ItemRejectStateRequest;
import com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.requests.OtherItemAskingRegistrationRequest;
import com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.services.OtherItemAskingRequestAllService;
import com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.services.OtherItemAskingRequestService;
import com.azu.hospital.utils.enums.OtherItemRequest.OtherItemRequestPlaces;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/items-request")
@CrossOrigin
public class OtherItemAskingRequestController {


    private final OtherItemAskingRequestService service;
    private final OtherItemAskingRequestAllService allService;

    public OtherItemAskingRequestController(
            OtherItemAskingRequestService askingRequestService,
            OtherItemAskingRequestAllService allService
    ) {
        this.service = askingRequestService;
        this.allService = allService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRequest(@RequestParam  Long itemId,
                           @Valid @RequestBody AddRequestRequest request
                           ){
        service.addNewRequest(itemId, request.userId(), request.quantity(), request.itemPlace() , request.placeId() ,
                request.note());
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateRequest(@RequestParam Long requestId, @RequestBody OtherItemAskingRegistrationRequest request){
        allService.updateExistingRequest(requestId, request);
    }

    @GetMapping("/{requestId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<?> getByRequestId(@PathVariable Long requestId){
        return ResponseEntity.ok(allService.getOtherItemAskingRequestById(requestId));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', " +
            "'INVENTORY_CLERK', 'HOSPITAL_MANAGER')")
    public ResponseEntity<Page<OtherItemAskingRequestDto>> getAllRequests(
            @PageableDefault Pageable pageable
    ){
        return ResponseEntity.ok(allService.getAllRequest(pageable));
    }

    @PutMapping("/accept-request-or-reject")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<?> acceptRequest(@RequestParam Long requestId,
                                                @RequestBody ItemRejectStateRequest request){
        return ResponseEntity.ok(service.acceptOrRejectRequest(requestId, request));
    }

    @PutMapping("/preparing-request")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<?> preparingRequest(@RequestParam Long requestId, @RequestParam String status){
        return ResponseEntity.ok(service.preparingTheItemRequest(requestId, status));
    }

    @PutMapping("/cancel-request")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<?> cancelRequest(@RequestParam Long requestId, @RequestParam String status){
        return ResponseEntity.ok(service.cancelRequest(requestId, status));
    }

    @PutMapping("/received-or-not-received-request")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<?> receivedOrNotReceivedRequest(@RequestParam Long requestId, @RequestParam String status){
        return ResponseEntity.ok(service.receivedOrNotReceivedTheItemRequest(requestId, status));
    }

}
