package com.azu.hospital.ph.StockMangment.entringItemList;


import com.azu.hospital.ph.StockMangment.entringItemList.dto.StockBillDto;
import com.azu.hospital.ph.StockMangment.entringItemList.request.StockRegistrationRequest;
import com.azu.hospital.ph.StockMangment.entringItemList.request.StockUpdateRequest;
import com.azu.hospital.ph.StockMangment.entringItemList.services.AddBillServices;
import com.azu.hospital.ph.StockMangment.entringItemList.services.SockBillService;
import com.azu.hospital.ph.StockMangment.entringItemList.services.UpdateBillService;
import com.azu.hospital.utils.enums.StockBillState;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/v1/bill")
@CrossOrigin
public class StockBillController {
    private final SockBillService sockBillService;
    private final AddBillServices addBillServices;
    private final UpdateBillService updateBillService;

    @Autowired
    public StockBillController(SockBillService sockBillService, AddBillServices addBillServices, UpdateBillService updateBillService) {
        this.sockBillService = sockBillService;
        this.addBillServices = addBillServices;
        this.updateBillService = updateBillService;
    }

    @PostMapping
//    @PreAuthorize(value = "hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', " +
//            "'INVENTORY_CLERK', 'ACCOUNTANT', 'ACCOUNTING_CLERK', 'ACCOUNTING_MANAGER', 'ACCOUNT_EXECUTIVE', 'ACCOUNT_MANAGER')")
    public ResponseEntity<Void> createNewBill(@Valid @RequestBody StockRegistrationRequest registrationRequest){
         addBillServices.addNewBill(registrationRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{billId}")
//    @PreAuthorize(value = "hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', " +
//            "'INVENTORY_CLERK', 'ACCOUNTANT', 'ACCOUNTING_CLERK', 'ACCOUNTING_MANAGER', 'ACCOUNT_EXECUTIVE', 'ACCOUNT_MANAGER')")
//    @Secured({"HOSPITAL_MANAGER", "PHARMACISTS", "PHARMACISTS_ASSISTANT", "ADMINISTRATOR",
//            "INVENTORY_CLERK", "ACCOUNTANT", "ACCOUNTING_CLERK", "ACCOUNTING_MANAGER", "ACCOUNT_EXECUTIVE",
//            "ACCOUNT_MANAGER"})
    public ResponseEntity<?> getBillById(@PathVariable Long billId) {
        return ResponseEntity.ok(sockBillService.getBillById(billId));
    }


    @GetMapping
//    @PreAuthorize(value = "hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', " +
//            "'INVENTORY_CLERK', 'ACCOUNTANT', 'ACCOUNTING_CLERK', 'ACCOUNTING_MANAGER', 'ACCOUNT_EXECUTIVE', 'ACCOUNT_MANAGER')")
    public ResponseEntity<Page<StockBillDto>> getAllBills(
            @RequestParam(required = false, defaultValue = "")String pharmacyName,
            @RequestParam(required = false) String supplierDetails,
            @RequestParam(required = false) String billEntryDate,
            @RequestParam(required = false) BigDecimal billNumber,
            @RequestParam(required = false) StockBillState state,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StockBillDto> drugsDTOPage = sockBillService.findByBillBY(pharmacyName,supplierDetails, billEntryDate, billNumber,state, pageable);
        return ResponseEntity.ok().body(drugsDTOPage);
    }



    @PutMapping("{billId}")
    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize(value = "hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', " +
//            "'INVENTORY_CLERK', 'ACCOUNTANT', 'ACCOUNTING_CLERK', 'ACCOUNTING_MANAGER', 'ACCOUNT_EXECUTIVE', 'ACCOUNT_MANAGER')")
    public void updateList(
            @PathVariable("billId") Long billId,
            @RequestBody StockUpdateRequest updateRequest
    ) {
        updateBillService.stockBillUpdate(billId,updateRequest);
    }


    @DeleteMapping("/{billId}")
//    @PreAuthorize(value = "hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', " +
//            "'INVENTORY_CLERK', 'ACCOUNTANT', 'ACCOUNTING_CLERK', 'ACCOUNTING_MANAGER', 'ACCOUNT_EXECUTIVE', 'ACCOUNT_MANAGER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBillById(@PathVariable Long billId) {
        sockBillService.deleteBillBYId(billId);
    }



    @GetMapping("/get-all")
//    @PreAuthorize(value = "hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', " +
//            "'INVENTORY_CLERK', 'ACCOUNTANT', 'ACCOUNTING_CLERK', 'ACCOUNTING_MANAGER', 'ACCOUNT_EXECUTIVE', 'ACCOUNT_MANAGER')")
    public ResponseEntity<?> getAllBillBy(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "type", defaultValue = "drug") String type
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(sockBillService.getAllBillBy(type, pageable));
    }
}
