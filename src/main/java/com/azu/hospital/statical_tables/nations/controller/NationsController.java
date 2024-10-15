package com.azu.hospital.statical_tables.nations.controller;


import com.azu.hospital.statical_tables.nations.service.ExcelFileServiceNations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/nations")
@CrossOrigin
public class NationsController {
    private final ExcelFileServiceNations excelFileServiceNations;


    @Autowired
    public NationsController(@Qualifier("excelFileServiceNations") ExcelFileServiceNations excelFileServiceNations) {
        this.excelFileServiceNations = excelFileServiceNations;
    }


    @PostMapping("/load-data")
    public ResponseEntity<String> loadData() {
        try {
            excelFileServiceNations.loadNationsFile();
            return ResponseEntity.ok("Data loaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while loading data" + e.getMessage());
        }
    }


    @GetMapping("/search")
    public ResponseEntity<?> search(
                @RequestParam(value = "nationName", required = false) String nationName,
            @RequestParam(value = "nationCode", required = false) Long nationCode,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(excelFileServiceNations.searchNations(nationName, nationCode,pageable));
    }





}
