package com.azu.hospital.books.dto;

import com.azu.hospital.books.attachments.dto.AttachmentsDto;
import com.azu.hospital.books.bookSigning.dto.BookSigningDto;
import com.azu.hospital.books.copyTo.dto.CopyToDto;
import com.azu.hospital.utils.enums.book.BookState;
import com.azu.hospital.utils.enums.book.BookType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class BookDto {

    private Long bookId;

    private String title;

    private String content;

    private String directorateName;

    private String directorateNameTo;

    private BookState state;

    private Long ownerId;

    private String ownerName;

    private String hospitalName;

    private Instant createdAt;

    private Instant updatedAt;


    private BookType type;

    private List<BookSigningDto> bookSigningList;

    private List<AttachmentsDto> attachments;

    private List<CopyToDto> copyTo;


    public BookDto(Long bookId, String title, String content, String directorateName, String directorateNameTo, BookState state, Long ownerId, String ownerName, String hospitalName, Instant createdAt, Instant updatedAt, BookType type, List<BookSigningDto> bookSigningList, List<AttachmentsDto> attachments, List<CopyToDto> copyTo) {
        this.bookId = bookId;
        this.title = title;
        this.content = content;
        this.directorateName = directorateName;
        this.directorateNameTo = directorateNameTo;
        this.state = state;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.hospitalName = hospitalName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.type = type;
        this.bookSigningList = bookSigningList;
        this.attachments = attachments;
        this.copyTo = copyTo;
    }

    public BookDto() {
    }


}