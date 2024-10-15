package com.azu.hospital.ph.StockMangment.Consumbles.barcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/consumable-brocade")
@CrossOrigin
public class ConsumableBarcodeController {


    private final ConsumablesBarcodeService barcodeService;

    @Autowired
    public ConsumableBarcodeController(@Qualifier("consumablesBarcodeService") ConsumablesBarcodeService barcodeService) {
        this.barcodeService = barcodeService;
    }

    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getBarcode() {
        try {
            final int width = 70;
            final int height = 35;
            byte[] barcodeImage = barcodeService.generateCode128BarcodeImage(width, height);

            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(barcodeImage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
