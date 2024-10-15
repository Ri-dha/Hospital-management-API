package com.azu.hospital.books.controller;

import com.azu.hospital.books.request.CreateNewBookRequest;
import com.azu.hospital.books.request.UpdateNewBookRequest;
import com.azu.hospital.books.services.BookAddService;
import com.azu.hospital.books.services.BookGetService;
import com.azu.hospital.books.services.BookStatesService;
import com.azu.hospital.books.services.BookUpdateService;
import com.azu.hospital.utils.enums.book.BookState;
import com.azu.hospital.utils.enums.book.BookType;
import com.azu.hospital.utils.enums.book.SigningState;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/book")
@CrossOrigin
public class BookController {
    private final BookAddService addService;
    private final BookGetService getService;
    private final BookUpdateService updateService;

    private final BookStatesService statesService;


    @Autowired
    public BookController(BookAddService addService, BookGetService getService, BookUpdateService updateService, BookStatesService statesService) {
        this.addService = addService;
        this.getService = getService;
        this.updateService = updateService;
        this.statesService = statesService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBook(
            @RequestBody CreateNewBookRequest request){
        addService.createNewBook(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<?> getBookById(
            @RequestParam Long bookId){
        return ResponseEntity.ok(getService.getBookById(bookId));
    }

    @GetMapping("/get-all-need-to-sign")
    public ResponseEntity<?> getAllBooksNeedToSign(
            @RequestParam Long usersToSignId){
        return ResponseEntity.ok(getService.getAllBooksNeedToSign(usersToSignId));
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long ownerId,
            @RequestParam(required = false) Long singerId,
            @RequestParam(required = false) List<BookState> bookStates,
            @RequestParam(required = false) List<BookType> bookTypes){
        return ResponseEntity.ok(getService.getAllWithFilter(title, ownerId, singerId, bookStates, bookTypes));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBook(
            @RequestParam Long bookId,
            @RequestBody UpdateNewBookRequest request){
        updateService.updateBook(bookId, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/state")
    public ResponseEntity<?> updateBookState(
            @RequestParam Long bookId,
            @RequestParam String state){
        statesService.archiveBook(bookId, state);
        return ResponseEntity.ok().build();
    }
}
