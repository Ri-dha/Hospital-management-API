package com.azu.hospital.accounting.all_item_expanse.base_expanse.controller;

import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.ExpanseDtoScreen;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.FinalPatientExpensesDtoScreen;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.PatientExpanseDtoScreen;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.PatientExpanseFinalListStrategy;
import com.azu.hospital.accounting.all_item_expanse.opreation.PatientOperationExpanseResultTableUpdateRequest;
import com.azu.hospital.accounting.all_item_expanse.opreation.service.UpdatePatientOperationExpanseResultTableService;
import com.azu.hospital.accounting.all_item_expanse.other_consumbles.request.PatientOtherConsumablesResultTableUpdateRequest;
import com.azu.hospital.accounting.all_item_expanse.other_consumbles.service.UpdatePatientOtherConsumablesResultTableServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/prices")
@CrossOrigin
public class PatientExpanseFinalListStrategyController {


    private final PatientExpanseFinalListStrategy strategy;
    private final UpdatePatientOtherConsumablesResultTableServices updatePatientOtherConsumablesResultTableServices;

    private final UpdatePatientOperationExpanseResultTableService updatePatientOperationExpanseResultTableService;
    @Autowired
    public PatientExpanseFinalListStrategyController(PatientExpanseFinalListStrategy strategy, UpdatePatientOtherConsumablesResultTableServices updatePatientOtherConsumablesResultTableServices, UpdatePatientOperationExpanseResultTableService updatePatientOperationExpanseResultTableService) {
        this.strategy = strategy;
        this.updatePatientOtherConsumablesResultTableServices = updatePatientOtherConsumablesResultTableServices;
        this.updatePatientOperationExpanseResultTableService = updatePatientOperationExpanseResultTableService;
    }


    @GetMapping("/print-bills")
    public ResponseEntity<List<ExpanseDtoScreen>> getPatientExpenses(@RequestParam Long patientId) {
        List<ExpanseDtoScreen> expenses = strategy.getAllExpensesByPatientId(patientId);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/print-bills-with-patient-data")
    public ResponseEntity<PatientExpanseDtoScreen> getPatientExpensesWithPatientData(@RequestParam Long patientId)  {
        PatientExpanseDtoScreen expenses = strategy.getWithPatientData(patientId);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/print-archived-bills")
    public ResponseEntity<PatientExpanseDtoScreen> getArchivedPatientExpenses(@RequestParam Long patientId)  {
        PatientExpanseDtoScreen expenses = strategy.getArchivedWithPatientData(patientId);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/print-bills-by-date")
    public ResponseEntity<FinalPatientExpensesDtoScreen> getPatientExpensesByDate(
            @RequestParam Long patientId,
            @RequestParam LocalDate date,
            @RequestParam Long entryTableId
    ) throws Exception {
        FinalPatientExpensesDtoScreen expenses = strategy.getWithPatientDataAndDate(patientId,entryTableId ,date);
        return ResponseEntity.ok(expenses);
    }

    @PutMapping("/pay-bills")
    public ResponseEntity<String> payPatientExpenses(@RequestParam Long patientId) {
        strategy.payExpanseFinalList(patientId);
        return ResponseEntity.ok("paid");
    }


    @PutMapping("/update-pricing-other-consumables")
    public ResponseEntity<Void> updatePricing(
            @RequestBody List<PatientOtherConsumablesResultTableUpdateRequest> requests){
        updatePatientOtherConsumablesResultTableServices.updatePricing(requests);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/update-pricing-operation")
    public ResponseEntity<Void> updatePricingOperation(
            @RequestBody List<PatientOperationExpanseResultTableUpdateRequest> requests){
        updatePatientOperationExpanseResultTableService.updatePricing(requests);
        return ResponseEntity.ok().build();
    }
}
