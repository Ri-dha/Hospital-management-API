package com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.controller;


import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.request.CustomSalaryRequest;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.dto.MainSalaryDto;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.dto.MainSalaryTableDto;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.request.MainSalaryTableAddRequest;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.request.MainSalaryTableUpdateRequest;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.service.MainSalaryTableGetService;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.service.MainSalaryTableAddService;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.service.MainSalaryTableService;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.service.MainSalaryTableUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.time.YearMonth;
import java.util.List;

@RequestMapping("/api/v1/mainSalaryTable")
@RestController
@CrossOrigin
public class MainSalaryTableController {
    private final MainSalaryTableAddService service;
    private final MainSalaryTableGetService getService;
    private final MainSalaryTableUpdateService updateService;

    private final MainSalaryTableService mainSalaryTableService;

    @Autowired
    public MainSalaryTableController(MainSalaryTableAddService service, MainSalaryTableGetService getService, MainSalaryTableUpdateService updateService, MainSalaryTableService mainSalaryTableService) {
        this.service = service;
        this.getService = getService;
        this.updateService = updateService;
        this.mainSalaryTableService = mainSalaryTableService;
    }

    @PostMapping("/add")
    public void addListOfMainSalaryTable(
            @RequestParam Long userId,
            @RequestBody MainSalaryTableAddRequest request){
        service.addMainSalaryTable(userId,request);
    }


    @PutMapping("/update")
    public void updateMainSalaryTable(
            @RequestParam Long id,
            @RequestBody MainSalaryTableUpdateRequest request){
        updateService.updateMainSalaryTable(id,request);
    }


    @GetMapping("/get-total-salary")
    public MainSalaryDto getTotalSalary(
            @RequestParam Long userId
            ){
        return mainSalaryTableService.getSalaryComponent(userId);
    }

    @GetMapping("/get-by-month")
    public MainSalaryTableDto getByUserIdAndMonth(
            @RequestParam Long userId,
            @RequestParam YearMonth month){
        return getService.getByUserIdAndMonth(userId,month);
    }

}
