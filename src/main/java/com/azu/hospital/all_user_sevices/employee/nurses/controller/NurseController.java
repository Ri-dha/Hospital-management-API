package com.azu.hospital.all_user_sevices.employee.nurses.controller;

import com.azu.hospital.all_user_sevices.employee.nurses.dto.NurseDto;
import com.azu.hospital.all_user_sevices.employee.nurses.services.NurseAddServices;
import com.azu.hospital.all_user_sevices.employee.nurses.services.NurseService;
import com.azu.hospital.all_user_sevices.employee.nurses.services.NurseUpdateService;
import com.azu.hospital.all_user_sevices.regestrations_request.AllUserRegistrationRequest;
import com.azu.hospital.all_user_sevices.specialist_service.HospitalSpecialty;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Nurse" , description = "Nurse Curd")
@RestController
@RequestMapping("api/v1/nurses")
@CrossOrigin
public class NurseController {

    private final NurseAddServices addServices;
    private final NurseUpdateService updateService;
    private final NurseService nurseService;

    @Autowired
    public NurseController(
            @Qualifier("NurseAddService") NurseAddServices addServices,
            @Qualifier("NurseUpdateService") NurseUpdateService updateService,
            @Qualifier("NurseService") NurseService nurseService
    )
    {
        this.addServices = addServices;
        this.updateService = updateService;
        this.nurseService = nurseService;
    }


    @PostMapping(path = "/add", consumes = "multipart/form-data")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER', 'ADMINISTRATIVE_MANAGER')")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewNurse(@Valid @ModelAttribute AllUserRegistrationRequest request) throws IOException
    {
        addServices.createNewNurse(request);

    }


    @PutMapping(value = "/{nurseId}", consumes = "multipart/form-data")
    @ResponseStatus(value = HttpStatus.CREATED)
     @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER', 'ADMINISTRATIVE_MANAGER')")
    public void updateExistNurse(
            @Valid
            @ModelAttribute AllUserRegistrationRequest request,
            @PathVariable(value = "nurseId") Long nurseId
    ) throws IOException {
        updateService.updateExistNurse(request, nurseId);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER', 'ADMINISTRATIVE_MANAGER', 'DOCTOR')")
    public ResponseEntity<?> getAllNurses(
            @RequestParam(defaultValue = "10",value = "size") int size,
            @RequestParam(defaultValue = "0",value = "page") int page,
            @RequestParam(required = false,value = "username") String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String specialist,
            @RequestParam(required = false) String bloodGroup,
            @RequestParam(required = false) String mobile,
            @RequestParam(required = false) String gender
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NurseDto> response = nurseService.getAllNurses(
                pageable,
                username,
                email,
                specialist,
                bloodGroup,
                mobile,
                gender
        );
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER', 'ADMINISTRATIVE_MANAGER')")
    @GetMapping("/hospital_specialty")
    public List<String> getAllHospitalSpecialty(){
        return Arrays.stream(HospitalSpecialty.values())
                .map(HospitalSpecialty::getDisplayName)
                .collect(Collectors.toList());
    }

}
