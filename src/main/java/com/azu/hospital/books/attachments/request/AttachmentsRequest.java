package com.azu.hospital.books.attachments.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record AttachmentsRequest(
        @NotNull(message = "name required")
        String name,
        @NotNull(message = "files required")
         List<MultipartFile> files

){
}
