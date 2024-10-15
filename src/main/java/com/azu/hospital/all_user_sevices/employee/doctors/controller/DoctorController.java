package com.azu.hospital.all_user_sevices.employee.doctors.controller;

import com.azu.hospital.all_user_sevices.employee.doctors.dto.CustomDoctorSpecialDto;
import com.azu.hospital.all_user_sevices.employee.doctors.dto.DoctorDto;
import com.azu.hospital.all_user_sevices.employee.doctors.services.DoctorAddServices;
import com.azu.hospital.all_user_sevices.employee.doctors.services.DoctorService;
import com.azu.hospital.all_user_sevices.employee.doctors.services.DoctorUpdateService;
import com.azu.hospital.all_user_sevices.regestrations_request.AllUserRegistrationRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Tag(name = "Doctor" , description = "Doctor Curd")
@RestController
@RequestMapping("api/v1/doctors")
@CrossOrigin
public class DoctorController {

    private final DoctorAddServices addServices;
    private final DoctorUpdateService updateService;
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(
            @Qualifier("DoctorAddService") DoctorAddServices addServices,
            @Qualifier("DoctorUpdateService") DoctorUpdateService updateService,
            @Qualifier("DoctorService") DoctorService doctorService
    )
    {
        this.addServices = addServices;
        this.updateService = updateService;
        this.doctorService = doctorService;
    }


    @PostMapping(path = "/add", consumes = "multipart/form-data")
//    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER', 'ADMINISTRATIVE_MANAGER', 'ADMINISTRATIVE', 'ACCOUNTANT')")
    public ResponseEntity<Void> addNewDoctor(@Valid @ModelAttribute AllUserRegistrationRequest request) throws IOException
    {
        addServices.createNewDoctor(request);
        return ResponseEntity.ok().build();
    }


    @PutMapping(value = "/{doctorId}", consumes = "multipart/form-data")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER', 'ADMINISTRATIVE_MANAGER', 'ADMINISTRATIVE', 'ACCOUNTANT')")
    public ResponseEntity<Void> updateExistDoctor(
            @Valid
            @ModelAttribute AllUserRegistrationRequest request,
            @PathVariable(value = "doctorId") Long doctorId
    ) throws IOException {
        updateService.updateExistDoctor(request, doctorId);
        return ResponseEntity.ok().build();
    }


    @GetMapping
//    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER', 'ADMINISTRATIVE_MANAGER', 'ADMINISTRATIVE'," +
//            " 'ACCOUNTANT', 'DEPARTMENT_MANAGER', 'PHYSICAL_THERAPY_MANAGER', 'DEPARTMENT_MANAGER_ASSISTANT', 'PHYSICAL_THERAPY_MANAGER_ASSIST'," +
//            " 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', 'ANESTHETIC_HEAD_CHIEF', 'WARD_MANAGER', 'PERMANENT', " +
//            "'RECEPTIONIST'," +
//            "'WARD_MANAGER_ASSISTANT')")
    public ResponseEntity<?> getAllDoctors(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String specialist,
            @RequestParam(required = false) String subSpecialist,
            @RequestParam(required = false) String bloodGroup,
            @RequestParam(required = false) String mobile,
            @RequestParam(required = false) String gender
    ) {
        Page<DoctorDto> response = doctorService.getAllDoctors(
                size,
                page,
                username,
                email,
                specialist,
                subSpecialist,
                bloodGroup,
                mobile,
                gender
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getTop10(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page
    ){
        Page<CustomDoctorSpecialDto> doctor = doctorService.getAllDoctors(size, page);
        return ResponseEntity.ok(doctor);

    }

//    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER', 'ADMINISTRATIVE_MANAGER')")
    @GetMapping("/ids")
    public ResponseEntity<?> getListOfDoctorByIds(
            @RequestParam(name = "ids")List<Long> ids
            ){
        return ResponseEntity.ok(doctorService.getListOfDoctorByIds(ids));
    }

}
