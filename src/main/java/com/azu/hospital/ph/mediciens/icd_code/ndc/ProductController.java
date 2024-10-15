package com.azu.hospital.ph.mediciens.icd_code.ndc;

import com.azu.hospital.ph.mediciens.icd_code.ndc.request.CreateProdcutRecord;
import com.azu.hospital.ph.mediciens.icd_code.ndc.service.ExcelUtility;
import com.azu.hospital.ph.mediciens.icd_code.ndc.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ndc")
@CrossOrigin
public class ProductController {

    private final ExcelUtility excelUtility;
    private final ProductService productService;

    @Autowired
    public ProductController(ExcelUtility excelUtility, ProductService productService) {
        this.excelUtility = excelUtility;
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity<Void> createProduct(@Valid @ModelAttribute CreateProdcutRecord request) {
        productService.createProduct(request);
        return ResponseEntity.ok().build();
    }



    @PostMapping("/load-data")
    public ResponseEntity<String> loadData() {

        try {
            excelUtility.loadProductsFromExcel();
            return ResponseEntity.ok("Data loaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Error while loading data" + e.getMessage());
        }
    }
}
