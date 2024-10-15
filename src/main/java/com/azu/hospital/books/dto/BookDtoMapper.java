package com.azu.hospital.books.dto;

import com.azu.hospital.books.attachments.dto.AttachmentsDtoMapper;
import com.azu.hospital.books.bookSigning.dto.BookSigningDtoMapper;
import com.azu.hospital.books.copyTo.dto.CopyToDtoMapper;
import com.azu.hospital.books.entity.Book;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BookDtoMapper implements Function<Book,BookDto> {
    private final BookSigningDtoMapper bookSigningDtoMapper;
    private final AttachmentsDtoMapper attachmentsDtoMapper;
    private final CopyToDtoMapper copyToDtoMapper;

    public BookDtoMapper(BookSigningDtoMapper bookSigningDtoMapper, AttachmentsDtoMapper attachmentsDtoMapper, CopyToDtoMapper copyToDtoMapper) {
        this.bookSigningDtoMapper = bookSigningDtoMapper;
        this.attachmentsDtoMapper = attachmentsDtoMapper;
        this.copyToDtoMapper = copyToDtoMapper;
    }

    @Override
    public BookDto apply(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getBookBody(),
                book.getDirectorateName(),
                book.getDirectorateNameTo(),
                book.getState(),
                book.getBookOwner().getId(),
                book.getBookOwner().getUsername(),
                book.getHospitalInfo() == null ? null : book.getHospitalInfo().getName(),
                book.getCreatedAt(),
                book.getUpdateAt(),
                book.getType(),
                book.getBookSignings().
                        stream()
                        .map(bookSigningDtoMapper).
                        toList(),
                book.getAttachments()
                        .stream()
                        .map(attachmentsDtoMapper)
                        .toList(),
                book.getCopyTo()
                        .stream()
                        .map(copyToDtoMapper)
                        .toList()
        );
    }
}
