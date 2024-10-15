//package com.azu.hospital.bulding.department.HospitalSettingsController;
//
//import com.azu.hospital.bulding.department.request.UnitApiResponse;
//import com.azu.hospital.bulding.department.request.UnitBaseRequest;
//import com.azu.hospital.bulding.department.services.UnitServices;
//import jakarta.validation.Valid;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("api/vi/unit")
//public class UnitController {
//    private final UnitServices unitServices;
//
//    public UnitController(UnitServices unitServices) {
//        this.unitServices = unitServices;
//    }
//
//    @PostMapping
//    public ResponseEntity<UnitApiResponse> addNewUint(@Valid
//            @RequestParam Long depId,
//            @RequestBody UnitBaseRequest request
//    ){
//        UnitApiResponse response= unitServices.createNewUnit(depId,request);
//        return ResponseEntity.ok(response);
//    }
//}
