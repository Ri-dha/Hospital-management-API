package com.azu.hospital.books.attachments.dto;


import com.azu.hospital.books.attachments.entity.Attachments;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AttachmentsDtoMapper implements Function<Attachments, AttachmentsDto> {
    @Override
    public AttachmentsDto apply(Attachments attachments) {
        return new AttachmentsDto(
                attachments.getId(),
                attachments.getBook().getId(),
                attachments.getName(),
                attachments.getFiles(),
                attachments.getCreatedAt()
        );
    }
}
