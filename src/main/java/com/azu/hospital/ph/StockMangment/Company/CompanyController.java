package com.azu.hospital.ph.StockMangment.Company;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/companies")
@CrossOrigin

public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<Map<String, Object>> getAllCompanies(Pageable pageable) {
        Map<String, Object> response = companyService.getAllCompanies(pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<Map<String, Object>> addNewCompany(@Valid @RequestBody Company company) {
        Map<String, Object> response = companyService.addNewCompany(company);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{companyId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<Map<String, Object>> updateExistingCompany(@Valid@PathVariable Integer companyId,
                                                                     @RequestBody Company company) {
        company.setCompanyId(companyId);
        Map<String, Object> response = companyService.updateExistingCompany(company);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{companyId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<Map<String, Object>> deleteCompany(@PathVariable Integer companyId) {
        Map<String, Object> response = companyService.deleteCompany(companyId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{companyId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<Map<String, Object>> findCompanyById(@PathVariable Integer companyId) {
        Map<String, Object> response = companyService.findCompanyById(companyId);
        HttpStatus status = response.containsKey("data") ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @GetMapping("/exists")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<Boolean> existsCompanyByEmail(@RequestParam String companyEmail) {
        boolean exists = companyService.existCompanyByEmail(companyEmail);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }
}
