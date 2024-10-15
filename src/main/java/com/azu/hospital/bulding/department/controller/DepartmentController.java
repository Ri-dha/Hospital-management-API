package com.azu.hospital.bulding.department.controller;

import com.azu.hospital.bulding.department.dto.DepartmentDto;
import com.azu.hospital.bulding.department.request.DepartmentBaseRequest;
import com.azu.hospital.bulding.department.services.DepartmentServices;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Department" , description = "Department Routes")
@RestController
@RequestMapping("api/v1/department")
@CrossOrigin
public class DepartmentController {

    private final DepartmentServices departmentServices;

    public DepartmentController(DepartmentServices departmentServices) {
        this.departmentServices = departmentServices;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DtoForReturnIdOnly> addNewDep(
            @Valid
            @RequestParam Long floorId,
            @ModelAttribute DepartmentBaseRequest request
    ) {
        return ResponseEntity.ok(departmentServices.createNewDep(floorId, request));
    }

    @PutMapping("/update/{depId}")
    public void updateDepartment(
            @PathVariable Long depId,
            @RequestParam(required = false) Long floorId,
            @ModelAttribute DepartmentBaseRequest update
    ) {
        departmentServices.updateDepartment(depId, floorId, update);
    }

    @GetMapping("/all")
    public ResponseEntity<List<?>> getAllDeps(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page
    ) {
        Pageable pageable = PageRequest.of(page, size);
        List<DepartmentDto> departmentDtoPage = departmentServices.getAllDepartments(pageable);
        return ResponseEntity.ok().body(departmentDtoPage);
    }

    @GetMapping("/{depId}")
    public ResponseEntity<?> getUserDepartment(@PathVariable("depId") Long depId) {
        return ResponseEntity.ok(departmentServices.getDepartmentById(depId));
    }

    @GetMapping("/dep/{userId}")
    public ResponseEntity<?> getDepById(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(departmentServices.getUserDep(userId));
    }


    @DeleteMapping("/{depId}")
    public void deleteFloor(@PathVariable Long depId) {
        departmentServices.deleteDepartmentById(depId);
    }
}
