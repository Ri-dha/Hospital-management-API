package com.azu.hospital.ph.StockMangment.Supplier;

import com.azu.hospital.ph.mediciens.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/suppliers")
@CrossOrigin
public class SupplierController {
    private final SupplierServices supplierServices;

    public SupplierController(SupplierServices supplierServices) {
        this.supplierServices = supplierServices;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<PageResponse<SupplierDto>> findBySupplierSortedOrderBy(
            @RequestParam(required = false) String supplierName,
            @RequestParam(required = false) String supplierEmail,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SupplierDto> supplierDtoPage = supplierServices.findBySupplierSortedOrderBy(supplierName, supplierEmail,pageable);

        PageResponse<SupplierDto> response = new PageResponse<>(
                supplierDtoPage.getContent(),
                supplierDtoPage.getTotalElements(),
                supplierDtoPage.getNumber(),
                supplierDtoPage.getSize(),
                supplierDtoPage.getTotalPages(),
                supplierDtoPage.hasPrevious(),
                supplierDtoPage.hasNext()

        );

        if (supplierDtoPage.hasPrevious()) {
            int previousPageNumber = supplierDtoPage.getNumber() - 1;
            String previousPageUrl = "https://web.azu-app.com" + previousPageNumber + "&size=" + size;
            response.setPreviousPageUrl(previousPageUrl);
        }

        if (supplierDtoPage.hasNext()) {
            int nextPageNumber = supplierDtoPage.getNumber() + 1;
            String nextPageUrl = "https://web.azu-app.com" + nextPageNumber + "&size=" + size;
            response.setNextPageUrl(nextPageUrl);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<?> registerSupplier(
            @RequestBody SupplierRegistrationRequest request) {
        supplierServices.CreateSupplier(request);
        boolean status = true;
        return ResponseEntity.ok().body("{\"status\": " + status + "}");
    }

    @GetMapping("{supplierId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public SupplierDto getSupplier(
            @PathVariable("supplierId") Integer supplierId
    ) {
        return supplierServices.getSupplierById(supplierId);
    }

    @DeleteMapping("{supplierId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<String> deleteSupplier(
            @PathVariable("supplierId") Integer supplierId) {
        supplierServices.deleteSupplierById(supplierId);
        boolean status = true;
        return ResponseEntity.ok().body("{\"status\": " + status + "}");
    }

    @PutMapping("{supplierId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<String> updateSupplier(
            @PathVariable("supplierId") Integer supplierId,
            @RequestBody SupplierUpdateRequest request
    ) {
        supplierServices.updateSupplier(supplierId,request);
        boolean status = true;
        return ResponseEntity.ok().body("{\"status\": " + status + "}");
    }

}
