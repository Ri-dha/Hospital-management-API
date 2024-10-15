package com.azu.hospital.lab_collection;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Lab" , description = "Lab Routes")
@RestController
@RequestMapping("api/v1/lab/home")
@CrossOrigin

public class LabController {

    private final LabHomeServices labHomeServices;

    @Autowired
    public LabController(LabHomeServices labHomeServices) {
        this.labHomeServices = labHomeServices;
    }

    @GetMapping("")
    public ResponseEntity<?> getHomeBlocks(){
        return ResponseEntity.of(Optional.ofNullable(labHomeServices.getHomeBlocks()));
    }
    @GetMapping("most-common-tests")
    public ResponseEntity<?> mostCommonTests(@RequestParam(defaultValue = "15") Integer maxSize){
        return ResponseEntity.of(Optional.ofNullable(labHomeServices.mostCommonAnalysis(maxSize)));
    }

    @GetMapping("date-time-test")
    public ResponseEntity<?> dayTimeAnalysis(@RequestParam(defaultValue = "15") Integer maxSize){
        return ResponseEntity.of(Optional.ofNullable(labHomeServices.dayTimeAnalysis()));
    }

}
