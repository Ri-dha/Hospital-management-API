package com.azu.hospital.books.attachments.controller;


import com.azu.hospital.books.attachments.entity.Attachments;
import com.azu.hospital.books.attachments.request.AttachmentsRequest;
import com.azu.hospital.books.attachments.service.AttachmentsGetService;
import com.azu.hospital.books.attachments.service.AttachmentsService;
import com.azu.hospital.books.attachments.service.AttachmentsUpdateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/attachments")
@CrossOrigin
public class AttachmentsController {
    private final AttachmentsService addService;
    private final AttachmentsUpdateService updateService;
    private final AttachmentsGetService getService;

    @Autowired
    public AttachmentsController(AttachmentsService addService, AttachmentsUpdateService updateService, AttachmentsGetService getService) {
        this.addService = addService;
        this.updateService = updateService;
        this.getService = getService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createAttachment(
            @RequestParam Long bookId,
            @Valid @ModelAttribute AttachmentsRequest request) throws IOException {
        addService.createAttachment(bookId, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAttachment(
            @RequestParam Long attachmentId,
            @RequestBody AttachmentsRequest request){
        updateService.updateAttachment(attachmentId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAttachmentsByBookId(
            @RequestParam Long bookId){
        return ResponseEntity.ok(getService.getAttachmentsByBookId(bookId));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAttachmentById(
            @RequestParam Long attachmentId){
        return ResponseEntity.ok(getService.getAttachmentById(attachmentId));
    }

}
