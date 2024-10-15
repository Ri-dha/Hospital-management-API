package com.azu.hospital.ph.StockMangment.entringItemList;

import com.azu.hospital.ph.StockMangment.entringItemList.services.MathService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/bill/math")
@CrossOrigin
public class MathController {
    // TODO: 11/11/2023 changes for the best 
    private final MathService mathService;

    public MathController(MathService mathService) {
        this.mathService = mathService;
    }

    @GetMapping("/month_year/debts")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<MathApiResponse> calculateTotalDebts(@RequestParam("date") String date1, @RequestParam(value = "type") String type) {
        LocalDate date = LocalDate.parse(date1);
        BigDecimal totalDebts = mathService.calculateAllDebts(date.getMonthValue(), date.getYear(), type);
        String message = "Total debts calculated successfully";
        boolean status = true;
        int statusCode = HttpStatusCode.valueOf(200).value();
        MathApiResponse response = new MathApiResponse(message, status, statusCode, totalDebts);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/month_year/purchase")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<MathApiResponse> calculateTotalPurchase(@RequestParam("date") String date1, @RequestParam(value = "type") String type) {
        LocalDate date = LocalDate.parse(date1);
        BigDecimal totalPurchase = mathService.calculateAllPurchase(date.getMonthValue(), date.getYear(), type);
        String message = "Total Purchase calculated successfully";
        boolean status = true;
        int statusCode = HttpStatusCode.valueOf(200).value();
        MathApiResponse response = new MathApiResponse(message, status, statusCode, totalPurchase);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/month_year/purchases_net_of_debts")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<MathApiResponse> calculateTotalProfits(@RequestParam("date") String date1, @RequestParam(value = "type") String type) {
        LocalDate date = LocalDate.parse(date1);
        BigDecimal totalPurchase = mathService.calculateTotalPurchasesNetOfDebts(date.getMonthValue(), date.getYear(), type);
        String message;
        boolean status = true;
        int statusCode = HttpStatusCode.valueOf(200).value();
        if (totalPurchase.compareTo(BigDecimal.ZERO) < 0) {
            message = "The result is a debt of " + totalPurchase.abs() + ".";
        } else {
            message = "The result is a purchase of " + totalPurchase + ".";
        }
        MathApiResponse response = new MathApiResponse(message, status, statusCode, totalPurchase);
        return ResponseEntity.ok().body(response);
    }


}
