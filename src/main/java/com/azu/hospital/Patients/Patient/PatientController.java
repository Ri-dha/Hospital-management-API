package com.azu.hospital.Patients.Patient;


import com.azu.hospital.Patients.Patient.dto.PatientDto;
import com.azu.hospital.Patients.Patient.request.CreatePatientRequest;
import com.azu.hospital.Patients.Patient.request.GetAllPatientRequest;
import com.azu.hospital.Patients.Patient.request.UpdatePatientRequest2;
import com.azu.hospital.Patients.Patient.services.PatientAddService;
import com.azu.hospital.Patients.Patient.services.PatientGetService;
import com.azu.hospital.Patients.Patient.services.PatientUpdateService;
import com.azu.hospital.utils.enums.patient.BillState;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "Patient", description = "Patient Routes")
@RestController
@RequestMapping("api/v1/patient")
@CrossOrigin
public class PatientController {

    private final PatientAddService addServices;
    private final PatientGetService getService;
    private final PatientUpdateService updateServices;
    private final HttpServletRequest httpServletRequest;

    @Autowired
    public PatientController(PatientAddService addServices, PatientGetService getService, PatientUpdateService updateServices, HttpServletRequest httpServletRequest) {
        this.addServices = addServices;
        this.getService = getService;
        this.updateServices = updateServices;
        this.httpServletRequest = httpServletRequest;
    }




    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})

    public ResponseEntity<?> addNewPatient(@Valid @ModelAttribute CreatePatientRequest request) throws IOException {
        return ResponseEntity.ok(addServices.createNewPatient(request));
    }


    @PutMapping(value = "/update", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})

    public void updatePatient(
            @Valid @ModelAttribute UpdatePatientRequest2 request
    ) throws IOException {
        updateServices.updatePatient(request, httpServletRequest);
    }

    @GetMapping("/{id}")

    public ResponseEntity<?> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(getService.getPatientById(id));
    }


    @GetMapping("/get-by-ward")

    public ResponseEntity<?> getAllPatientByWard(
            @RequestParam Long wardId,
            @RequestParam(required = false) Long doctorId,
            @RequestParam(defaultValue = "") String patientName,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "15") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(getService.getAllPatientByWard(wardId, patientName, doctorId, pageable));
    }


    @GetMapping("/get-archive-by-ward")
    public ResponseEntity<?> getAllPatientArchiveByWard(
            @RequestParam Long wardId,
            @RequestParam(required = false) Long doctorId,
            @RequestParam(defaultValue = "") String patientName,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "15") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(getService.getAllPatientArchiveByWard(wardId, patientName, doctorId, pageable));
    }


    @GetMapping

    public ResponseEntity<?> getAllPatient(
            @Valid @ModelAttribute GetAllPatientRequest request,
            @RequestParam(required = false) BillState billState
    ) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        return ResponseEntity.ok(getService.getAllPatient(pageable, billState ,request));

    }

    @GetMapping("/get-all-archived-filter")
    public ResponseEntity<?> getAllPatientArchive(@Valid @ModelAttribute GetAllPatientRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        return ResponseEntity.ok(getService.getAllPatientArchive(pageable, request));

    }

    @GetMapping("/by-doctor-id")
    public ResponseEntity<?> getAllPatientByDoctorId(@Valid @ModelAttribute GetAllPatientRequest request,
                                                     @RequestParam Long doctorId) {

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        return ResponseEntity.ok(getService.getPatientByDoctorId(pageable, doctorId, request));

    }

    @GetMapping("/by-doctor-id-archive")
    public ResponseEntity<?> getAllPatientArchivedByDoctorId(
            @Valid @ModelAttribute GetAllPatientRequest request,
            @RequestParam Long doctorId) {

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        return ResponseEntity.ok(getService.getPatientArchiveByDoctorId(pageable, doctorId, request));

    }

    @GetMapping("/get-all-archived")
    public ResponseEntity<?> getAllArchivedPatient(@RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "15") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(getService.getAllArchivedPatients(pageable));
    }

    @GetMapping("/get-all-with-filter")
    public ResponseEntity<Page<PatientDto>> getAllPatientsWithFilter(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "15") Integer size,
            @RequestParam(required = false) String patientName,
            @RequestParam(required = false) String doctorName,
            @RequestParam(required = false) String wardName

    ) {Pageable pageable = PageRequest.of(page, size);
        Page<PatientDto> patients = getService.getAllPatientWithFilter(patientName, doctorName, wardName, pageable);
        return ResponseEntity.ok(patients);
    }


    @PutMapping("/un-archive/{id}")
    public ResponseEntity<?> unArchivePatient(@PathVariable Long id) {
        getService.reEntryPatient(id);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/get-by-id")
    public ResponseEntity<?> getPatientAllById(
            @RequestParam Long id) {
        return ResponseEntity.ok(getService.getPatientAllById(id));
    }

    @GetMapping("/get-all-patients-with-filter")
    public ResponseEntity<?> getAllPatientWithFilter(
            @Valid @ModelAttribute GetAllPatientRequest request,
            @RequestParam(required = false) BillState billState
    ) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        return ResponseEntity.ok(getService.getAllPatientWithFilter(request, billState ,pageable));

    }

}
