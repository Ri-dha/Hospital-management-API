package com.azu.hospital.books.copyTo.controller;


import com.azu.hospital.books.copyTo.request.CopyToRequest;
import com.azu.hospital.books.copyTo.service.CopyToGetService;
import com.azu.hospital.books.copyTo.service.CopyToService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/copyTo")
@CrossOrigin
public class CopyToController {
    private final CopyToService service;
    private final CopyToGetService getService;

    public CopyToController(CopyToService service, CopyToGetService getService) {
        this.service = service;
        this.getService = getService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCopyTo(
            @RequestParam Long bookId,
            @RequestParam Long  departmentId){
        service.createCopyTo(bookId, departmentId);
        return ResponseEntity.ok().build();
    }
    


    @GetMapping("/get/{copyToId}")
    public ResponseEntity<?> getCopyToById(
            @PathVariable Long copyToId){
        return ResponseEntity.ok(getService.getCopyToById(copyToId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCopyTo(
            @RequestParam Long bookId){
        return ResponseEntity.ok(getService.getAllByBookId(bookId));
    }


}
