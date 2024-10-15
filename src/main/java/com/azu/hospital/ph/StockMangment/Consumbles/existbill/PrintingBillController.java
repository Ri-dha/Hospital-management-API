package com.azu.hospital.ph.StockMangment.Consumbles.existbill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/printingBill")
@CrossOrigin
public class PrintingBillController {

    private final PrintingBillServices printingBillServices;

    @Autowired
    public PrintingBillController(PrintingBillServices printingBillServices) {
        this.printingBillServices = printingBillServices;
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<PrintingBill> getBill(@PathVariable("requestId") Long requestId){
        return ResponseEntity.ok(printingBillServices.printingBill(requestId));
    }
}
