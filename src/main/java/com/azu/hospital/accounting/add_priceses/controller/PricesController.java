package com.azu.hospital.accounting.add_priceses.controller;

import com.azu.hospital.accounting.add_priceses.dto.PricesDto;
import com.azu.hospital.accounting.add_priceses.request.PricesBaseRequest;
import com.azu.hospital.accounting.add_priceses.request.PricesService;
import com.azu.hospital.accounting.add_priceses.services.IPrintFinalListServices;
import com.azu.hospital.accounting.add_priceses.services.LoadData;
import com.azu.hospital.accounting.add_priceses.services.PricesAddServices;
import com.azu.hospital.accounting.add_priceses.services.PrintFinalListServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/prices")
@CrossOrigin
public class PricesController{


    private final PricesAddServices addServices;
    private final PricesService pricesService;
    private final IPrintFinalListServices printFinalListServices;

    private final LoadData data;

    @Autowired
    public PricesController(
            PricesAddServices addServices,
            PricesService pricesService,
            @Qualifier("PrintFinalListServicesImp") IPrintFinalListServices printFinalListServices,
            LoadData data
    ) {
        this.addServices = addServices;
        this.pricesService = pricesService;
        this.printFinalListServices = printFinalListServices;
        this.data = data;
    }


    @PostMapping
    public ResponseEntity<Void> addPrices(@RequestBody PricesBaseRequest request) {
        addServices.addPrices(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePrices(@PathVariable Long id, @RequestBody PricesBaseRequest request) {
        addServices.updatePrices(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrices(@PathVariable Long id) {
        addServices.DeleteItemBiId(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllItemPrices(
            @RequestParam(required = false, defaultValue = "") String itemType,
            @RequestParam(required = false, defaultValue = "") String name,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<PricesDto> pricesList = pricesService.getAllItemPrices(itemType, name, pageable);
        return ResponseEntity.ok(pricesList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PricesDto> getItemPricesById(@PathVariable Long id) {
        PricesDto pricesDto = pricesService.getItemPricesById(id);
        return ResponseEntity.ok(pricesDto);
    }

    @GetMapping("/ph")
    public ResponseEntity<?> sumAllDrugsItem(@RequestParam Long patientId){
        return ResponseEntity.ok(printFinalListServices.getFinalPatientBills(patientId));
    }

    @GetMapping("/radiology")
    public ResponseEntity<?> sumAllRadiologyItem(@RequestParam Long patientId){
        return ResponseEntity.ok(printFinalListServices.getFinalPatientBillsRadiology(patientId));
    }

//    @GetMapping("/radiology/external")
//    public ResponseEntity<?> sumAllRadiologyItemExternal(@RequestParam Long patientId){
//        return ResponseEntity.ok(printFinalListServices.getFinalPatientBillsRadiologyExternal(patientId));
//    }


    @GetMapping("/ultrasound")
    public ResponseEntity<?> sumAllUltrasoundItem(@RequestParam Long patientId){
        return ResponseEntity.ok(printFinalListServices.getFinalPatientBillsUltrasound(patientId));
    }

    @GetMapping("/lab")
    public ResponseEntity<?> sumAllLabItem(@RequestParam Long patientId){
        return ResponseEntity.ok(printFinalListServices.getFinalPatientBillsLab(patientId));
    }

    @GetMapping("/other-consumables")
    public ResponseEntity<?> sumAllOtherConsumablesItem(@RequestParam Long patientId){
        return ResponseEntity.ok(printFinalListServices.getFinalPatientBillsOtherConsumables(patientId));
    }







//    @GetMapping("/print-bills")
//    public ResponseEntity<?> getAllItemPrices(
//            @RequestParam Long patientId
//    ) {
//        return ResponseEntity.ok( printFinalListServices.getFinalPatientBill(patientId));
//    }


//    @GetMapping("/operation")
//    public ResponseEntity<?> sumAllOperationItem(@RequestParam Long patientId){
//        return ResponseEntity.ok(printFinalListServices.getFinalPatientBillsOperation(patientId));
//    }


//    @PostMapping("LoadData")
//    public ResponseEntity<?> LoadData()throws Exception{
//        try {
//        data.LoadDataService();
//            return ResponseEntity.ok("Data loaded successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error occurred while loading data: " + e.getMessage());
//        }
//    }



    @GetMapping("/pay")
    public void test(
            @RequestParam Long patientId
    ){
//      printFinalListServices.payButtonProcess(patientId);
    }
}
