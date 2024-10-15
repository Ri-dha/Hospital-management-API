package com.azu.hospital.ph.main_data_store.drugs_item.inject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/inject-excel")
public class DrugsExcelController {


    private final DrugsExcelService excelService;

    @Autowired
    public DrugsExcelController(DrugsExcelService excelService) {
        this.excelService = excelService;
    }

    @PostMapping
    public ResponseEntity<String> importExcelData(@RequestBody MultipartFile file) {
        try {
            File convertedFile = convertMultipartFileToFile(file);
            excelService.importDataFromExcel(convertedFile.getAbsolutePath());
            return ResponseEntity.ok("Data imported successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred during data import.");
        }
    }

    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        }
        return convertedFile;
    }
}
