package com.azu.hospital.BarcodeGenerator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/barcode")
public class BarcodeGeneratorController {

    private final IBarcodeGenerator barcodeGenerator;

    public BarcodeGeneratorController(IBarcodeGenerator barcodeGenerator) {
        this.barcodeGenerator = barcodeGenerator;
    }


    @GetMapping("/generate")
    public ResponseEntity<?> generateBarcode() throws Exception {
        return ResponseEntity.ok(barcodeGenerator.generateEAN13BarcodeImage("123456789012"));
    }
}
