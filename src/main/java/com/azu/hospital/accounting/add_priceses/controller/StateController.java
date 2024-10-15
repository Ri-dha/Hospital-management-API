package com.azu.hospital.accounting.add_priceses.controller;


import com.azu.hospital.accounting.add_priceses.services.stateService.BillStateService;
import com.azu.hospital.utils.Dtos.StatusDto;
import com.azu.hospital.utils.enums.operation.OperationStates;
import com.azu.hospital.utils.enums.patient.BillState;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/bill/state")
@RestController
@CrossOrigin
public class StateController {
    private final BillStateService billStateService;


    public StateController(BillStateService billStateService) {
        this.billStateService = billStateService;
    }


    @PutMapping
    public ResponseEntity<?> midPaidState(
            @RequestParam Long id,
            @RequestParam String state) {
        return ResponseEntity.ok (billStateService.updateBillState(id, state));
    }

}
