package com.azu.hospital.ph.mediciens.icd_code.controller;

import com.azu.hospital.ph.mediciens.icd_code.entity.IcdCodClass;
import com.azu.hospital.ph.mediciens.icd_code.ndc.service.ProductService;
import com.azu.hospital.ph.mediciens.icd_code.ndc.dto.ProductDto;
import com.azu.hospital.ph.mediciens.icd_code.service.XmlDataParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/xml-data")
@CrossOrigin
public class XmlDataController {

    private final XmlDataParsingService xmlDataParsingService;

    private final ProductService productService;

    @Autowired
    public XmlDataController(XmlDataParsingService xmlDataParsingService, ProductService productService) {
        this.xmlDataParsingService = xmlDataParsingService;
        this.productService = productService;
    }


    @GetMapping("/retrieve")
    public ResponseEntity<Page<IcdCodClass>> retrieveXmlData(
            Pageable pageable,
            @RequestParam(name = "drugName", required = false) String searchTerm
    ) {
        try {
            Page<IcdCodClass> xmlDataPage;

            if (searchTerm != null && !searchTerm.isEmpty()) {
                xmlDataPage = xmlDataParsingService.searchByTitle(searchTerm, pageable);
            } else {
                xmlDataPage = xmlDataParsingService.parseXmlData(pageable);
            }

            if (xmlDataPage.hasContent()) {
                return ResponseEntity.ok(xmlDataPage);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


    @GetMapping("/titles")
    public ResponseEntity<Page<ProductDto>> getIcdCodes(
            @RequestParam(required = false, name = "drugName") String searchTerm,
            @RequestParam(required = false, name = "ndc") String ndc,
            Pageable pageable
    ) {
        try {
            Page<ProductDto> titles;
            titles = productService.findByproductNDCOrNonProprietaryNameContainingIgnoreCase(searchTerm,ndc,pageable);

            return ResponseEntity.ok(titles);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }













}