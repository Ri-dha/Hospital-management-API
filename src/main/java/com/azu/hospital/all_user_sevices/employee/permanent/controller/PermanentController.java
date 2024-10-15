package com.azu.hospital.all_user_sevices.employee.permanent.controller;

import com.azu.hospital.all_user_sevices.employee.permanent.dto.PermanentDto;
import com.azu.hospital.all_user_sevices.employee.permanent.services.PermanentAddServices;
import com.azu.hospital.all_user_sevices.employee.permanent.services.PermanentService;
import com.azu.hospital.all_user_sevices.employee.permanent.services.PermanentUpdateService;
import com.azu.hospital.all_user_sevices.regestrations_request.AllUserRegistrationRequest;
import com.azu.hospital.all_user_sevices.specialist_service.HospitalSpecialty;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Tag(name = "Permanent" , description = "Permanent Curd")
@RestController
@RequestMapping("api/v1/permanents")
@CrossOrigin
public class PermanentController {

    private final PermanentAddServices addServices;
    private final PermanentUpdateService updateService;
    private final PermanentService permanentService;

    @Autowired
    public PermanentController(
            @Qualifier("PermanentAddService") PermanentAddServices addServices,
            @Qualifier("PermanentUpdateService") PermanentUpdateService updateService,
            @Qualifier("PermanentService") PermanentService permanentService
    )
    {
        this.addServices = addServices;
        this.updateService = updateService;
        this.permanentService = permanentService;
    }


    @PostMapping(path = "/add", consumes = "multipart/form-data")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER', 'ADMINISTRATIVE_MANAGER')")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewPermanent(@Valid @ModelAttribute AllUserRegistrationRequest request) throws IOException
    {
         addServices.createNewPermanent(request);
    }

    @PutMapping(value = "/{permanentId}", consumes = "multipart/form-data")
    @ResponseStatus(value = HttpStatus.CREATED)
     @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER', 'ADMINISTRATIVE_MANAGER')")
    public void updateExistPermanent(
            @Valid
            @ModelAttribute AllUserRegistrationRequest request,
            @PathVariable(value = "permanentId") Long permanentId
    ) throws IOException {
        updateService.updateExistPermanent(request, permanentId);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
     @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER', 'ADMINISTRATIVE_MANAGER')")
    public ResponseEntity<?> getAllPermanents(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String specialist,
            @RequestParam(required = false) String bloodGroup,
            @RequestParam(required = false) String mobile,
            @RequestParam(required = false) String gender
    ) {
        Page<PermanentDto> response = permanentService.getAllPermanents(
                size,
                page,
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




