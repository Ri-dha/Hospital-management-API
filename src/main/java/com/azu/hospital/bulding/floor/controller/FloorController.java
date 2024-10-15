package com.azu.hospital.bulding.floor.controller;

import com.azu.hospital.bulding.floor.request.FloorApiResponse;
import com.azu.hospital.bulding.floor.request.FloorBaseRequest;
import com.azu.hospital.bulding.floor.dto.FloorDto;
import com.azu.hospital.bulding.floor.FloorServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Floor" , description = "Floor Routes")
@RestController
@RequestMapping("api/v1/floor")
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER', 'DOCTOR')")
@CrossOrigin
public class FloorController {
    private final FloorServices services;

    public FloorController(FloorServices services) {
        this.services = services;
    }


    @PostMapping
    public ResponseEntity<FloorApiResponse> addNewFloor(@Valid @RequestBody @ModelAttribute FloorBaseRequest request){

        FloorApiResponse response = services.createNewFloor(request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<FloorApiResponse> updateFloor( @RequestParam Long floorId, @RequestBody FloorBaseRequest request){
        FloorApiResponse response = services.updateExistsFloor(floorId, request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllFloors(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page
    ) {
        Pageable pageable = PageRequest.of(page, size);
        List<FloorDto> floorPagePage = services.selectAllFloors(pageable);
        return ResponseEntity.ok(floorPagePage);
    }

    @GetMapping ("/{floorId}")
    public ResponseEntity<FloorApiResponse> getFloorById(
            @PathVariable("floorId") Long floorId
    ) {
        FloorDto dto = services.getFloorById(floorId);
        FloorApiResponse response = new FloorApiResponse();
        response.setMessage("Successfully");
        response.setStatus(true);
        response.setStatusCode(HttpStatusCode.valueOf(200).value());
        response.setData(dto);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("{floorId}")
    public void deleteFloor(@PathVariable Long floorId){
        services.deleteFloor(floorId);
    }

    @GetMapping("/all-with-wards")
    public ResponseEntity<?> getAllFloorsWithWards(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(services.getAllFloorsWithWards(pageable));

    }


}

