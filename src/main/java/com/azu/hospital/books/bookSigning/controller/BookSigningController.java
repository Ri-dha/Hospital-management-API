package com.azu.hospital.books.bookSigning.controller;


import com.azu.hospital.books.bookSigning.request.BookSigningRequest;
import com.azu.hospital.books.bookSigning.service.BookSigningAddService;
import com.azu.hospital.books.bookSigning.service.BookSigningStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book-signing")
@CrossOrigin
public class BookSigningController {
    private final BookSigningAddService service;
    private final BookSigningStateService updateBookSigningState;

    @Autowired
    public BookSigningController(BookSigningAddService service, BookSigningStateService updateBookSigningState) {
        this.service = service;
        this.updateBookSigningState = updateBookSigningState;
    }



    @PostMapping("/create")
    public ResponseEntity<?> createBookSigning(
            @RequestParam(name = "bookId") Long bookId,
            @RequestBody List<BookSigningRequest> requests
    ){
        service.addBookSigning(bookId, requests);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/state")
    public ResponseEntity<?> updateBookSigningState(
            @RequestParam(name = "bookSigningId") Long bookSigningId,
            @RequestParam(name = "state") String state
    ){
        updateBookSigningState.signatureOrReject(bookSigningId, state);
        return ResponseEntity.ok().build();
    }



}
