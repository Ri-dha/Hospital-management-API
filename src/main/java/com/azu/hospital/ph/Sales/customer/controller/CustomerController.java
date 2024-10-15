package com.azu.hospital.ph.Sales.customer.controller;

import com.azu.hospital.ph.Sales.customer.dto.CustomerDto;
import com.azu.hospital.ph.Sales.customer.request.CustomerUpdateRequest;
import com.azu.hospital.ph.Sales.customer.services.CustomerAddService;
import com.azu.hospital.ph.Sales.customer.services.CustomerDeleteService;
import com.azu.hospital.ph.Sales.customer.services.CustomerGetService;
import com.azu.hospital.ph.Sales.customer.services.CustomerUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customers")
@CrossOrigin
public class CustomerController {
    private final CustomerAddService addService;
    private final CustomerUpdateService updateService;
    private final CustomerDeleteService deleteService;
    private final CustomerGetService getService;

    @Autowired
    public CustomerController(
            @Qualifier("customer_add_service")
            CustomerAddService addService,
            @Qualifier("customer_update_service")
            CustomerUpdateService updateService,
            @Qualifier("customer_delete_service")
            CustomerDeleteService deleteService,
            @Qualifier("customer_get_service")
            CustomerGetService getService
    ) {
        this.addService = addService;
        this.updateService = updateService;
        this.deleteService = deleteService;
        this.getService = getService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(
            @PathVariable("customerId") Long customerId
    ) {
        return ResponseEntity.ok(
                getService.getCustomerById(customerId)
        );
    }

    @GetMapping
    public ResponseEntity<Page<CustomerDto>> getAllCustomers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(getService.getAllCustomers(pageable));
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(
            @RequestParam("billId") Long billId,
            @RequestBody CustomerUpdateRequest request
    ) {
        addService.createCustomer(billId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(
            @RequestBody CustomerUpdateRequest request,
            @RequestParam(value = "customer_id", required = true) Long customerId
    ) {
        updateService.updateCustomer(request, customerId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{customer_id}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable("customer_id") Long customerId
    ){
        deleteService.deleteCustomerById(customerId);
        return ResponseEntity.ok().build();
    }
}
