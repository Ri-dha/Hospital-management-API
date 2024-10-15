package com.azu.hospital.books.patientBook.Controller;


import com.azu.hospital.books.patientBook.PatientBookType;
import com.azu.hospital.books.patientBook.request.PatientBookCreateRequest;
import com.azu.hospital.books.patientBook.request.PatientBookUpdateRequest;
import com.azu.hospital.books.patientBook.service.PatientBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/patient-book")
@CrossOrigin
public class PatientBookController {
    private final PatientBookService patientBookService;

    @Autowired
    public PatientBookController(@Qualifier("patientBookService") PatientBookService patientBookService) {
        this.patientBookService = patientBookService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createPatientBook(
            @ModelAttribute PatientBookCreateRequest request) {
        patientBookService.createPatientBook(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePatientBook(
            @ModelAttribute PatientBookUpdateRequest request) {
        patientBookService.updatePatientBook(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{patientBookId}")
    public ResponseEntity<?> deletePatientBook(
            @PathVariable Long patientBookId) {
        patientBookService.deletePatientBook(patientBookId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{patientBookId}")
    public ResponseEntity<?> getPatientBook(
            @PathVariable Long patientBookId) {
        return ResponseEntity.ok(patientBookService.getPatientBookById(patientBookId));
    }


    @GetMapping("/get-all")
    public ResponseEntity<?> getAllPatientBooks(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(patientBookService.getAllPatientBook(pageable));
    }

    @GetMapping("/get-filtered")
    public ResponseEntity<?> getFilteredPatientBooks(
            @RequestParam(required = false) Long patientId,
            @RequestParam(required = false) PatientBookType patientBookType,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(patientBookService.getPatientBookByTypeAndByPatientId(patientId, patientBookType, pageable));
    }


}
