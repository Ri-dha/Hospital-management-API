package com.azu.hospital.statical_tables.provinces.controller;


import com.azu.hospital.statical_tables.provinces.services.ExcelFileServiceProvinces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/provinces")
@CrossOrigin
public class ProvincesController {

    private final ExcelFileServiceProvinces provincesService;


    @Autowired
    public ProvincesController(@Qualifier("excelFileServiceProvinces") ExcelFileServiceProvinces provincesService) {
        this.provincesService = provincesService;
    }


    @PostMapping("/load-data")
    public ResponseEntity<String> loadData(){
        try {
            provincesService.loadProvincesFile();
            return ResponseEntity.ok("Data loaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error while loading data" + e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "provinceCode", required = false) Long provinceCode,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(provincesService.searchProvinces(name, provinceCode,pageable));
    }





}
