package com.azu.hospital.books.attachments.dto;

import com.azu.hospital.utils.files.File;

import java.time.Instant;
import java.util.List;

public record AttachmentsDto (
        Long id ,
        Long bookId ,
        String name,
        List<File>files,
        Instant createdAt

){
}
