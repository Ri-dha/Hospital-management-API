package com.azu.hospital.accounting.operationPercentage.controller;


import com.azu.hospital.accounting.operationPercentage.dto.OperationPercentageDto;
import com.azu.hospital.accounting.operationPercentage.entity.OperationPercentage;
import com.azu.hospital.accounting.operationPercentage.request.OperationPercentageCreateRequest;
import com.azu.hospital.accounting.operationPercentage.service.OperationPercentageService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/operation-percentage")
@CrossOrigin
public class OperationPercentageController {
    private final OperationPercentageService operationPercentageService;

    public OperationPercentageController(OperationPercentageService operationPercentageService) {
        this.operationPercentageService = operationPercentageService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createOperationPercentage(
            @RequestParam Long operationId,
            @RequestBody OperationPercentageCreateRequest request){
        operationPercentageService.createNewOperationPercentage(operationId,request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<OperationPercentageDto> getOperationPercentageById(Long operationId){

        return ResponseEntity.ok(operationPercentageService.getOperationPercentageById(operationId));
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateOperationPercentage(
            @RequestParam Long operationId,
            @RequestBody OperationPercentageCreateRequest request){
        operationPercentageService.updateOperationPercentage(operationId,request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteOperationPercentage(Long operationId){
        operationPercentageService.deleteOperationPercentage(operationId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-all")//Page
    public ResponseEntity<?> getAllOperationPercentages(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(operationPercentageService.getAllOperationPercentages(pageable));
    }


}
