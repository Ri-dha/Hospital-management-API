package com.azu.hospital.statical_tables.chronic_diseases.controller;

import com.azu.hospital.statical_tables.chronic_diseases.service.ExcelFileServiceChronicDiseases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/chronic-diseases")
@CrossOrigin
public class ChronicDiseasesController {
    private final ExcelFileServiceChronicDiseases chronicDiseasesService;

    @Autowired
    public ChronicDiseasesController(@Qualifier("excelFileServiceChronicDiseases") ExcelFileServiceChronicDiseases chronicDiseasesService) {
        this.chronicDiseasesService = chronicDiseasesService;
    }


    @PostMapping("/load-data")
    public ResponseEntity<String> loadData(){
        try {
            chronicDiseasesService.loadChronicDiseasesFile();
            return ResponseEntity.ok("Data loaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error while loading data" + e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam(value = "name", required = false) String diseaseName,
            @RequestParam(value = "chronicDiseaseCode", required = false) Long chronicDiseaseCode,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(chronicDiseasesService.searchChronicDiseases(diseaseName, chronicDiseaseCode, pageable));
    }



}
