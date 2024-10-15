package com.azu.hospital.statical_tables.diseases.controller;


import com.azu.hospital.statical_tables.diseases.service.DiseasesGetService;
import com.azu.hospital.statical_tables.diseases.service.ExcelFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/diseases")
@CrossOrigin
public class DiseasesController {

    private final ExcelFileService excelFileService;
    private final DiseasesGetService diseasesGetService;


    @Autowired
    public DiseasesController(@Qualifier("excelFileService") ExcelFileService excelFileService, @Qualifier("diseasesGetService") DiseasesGetService diseasesGetService) {
        this.excelFileService = excelFileService;
        this.diseasesGetService = diseasesGetService;
    }

    @PostMapping("/load-data")
    public ResponseEntity<String> loadData() {
        try {
            excelFileService.loadDiseasesFromExcel();
            return ResponseEntity.ok("Data loaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while loading data" + e.getMessage());
        }
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllDiseases(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(diseasesGetService.getDiseases(pageable));
    }


    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "serialNumber", required = false) String serialNumber,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(diseasesGetService.searchDiseases(name, code, serialNumber, pageable));
    }

}
