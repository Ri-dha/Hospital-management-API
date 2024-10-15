package com.azu.hospital.Patients.Patient;

import com.azu.hospital.Patients.Patient.services.BarcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/patient-brocade")
@CrossOrigin
public class BarcodeController {


    private final BarcodeService barcodeService;

    @Autowired
    public BarcodeController(@Qualifier("barcodeService") BarcodeService barcodeService) {
        this.barcodeService = barcodeService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getBarcode(@PathVariable Long id ) {
        try {
            final int width = 70;
            final int height = 35;
            byte[] barcodeImage = barcodeService.generateCode128BarcodeImage(id, width, height);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(barcodeImage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
