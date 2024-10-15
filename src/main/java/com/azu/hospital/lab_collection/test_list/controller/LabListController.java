package com.azu.hospital.lab_collection.test_list.controller;

import com.azu.hospital.lab_collection.test_list.request.CreateLabListRequest;
import com.azu.hospital.lab_collection.test_list.request.UpdateLabListRequest;
import com.azu.hospital.lab_collection.test_list.services.LabListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/lab/lab-list")
@CrossOrigin
public class LabListController {

    private final LabListService labListService;

    @Autowired
    public LabListController(LabListService labListService) {
        this.labListService = labListService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getLabList(
            @RequestParam(required = false, defaultValue = "") String testName,
            @RequestParam(defaultValue = "0") Integer page ,
            @RequestParam(defaultValue = "10") Integer size
            ){
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(labListService.getLabList(testName,pageable));
    }

    @PostMapping("/create")
    public void createManyLabs(@Valid @RequestBody CreateLabListRequest request){
        labListService.createManyLabList(request);
    }


    @PutMapping("/update")
    public void updateLabTest(
            @RequestParam("labId") Long labId,
            @Valid @RequestBody UpdateLabListRequest request){
        labListService.updateLabList(labId ,request);
    }


    @DeleteMapping("/delete")
    public void updateLabTest(@RequestParam Long id){
        labListService.deleteById(id);
    }
}
