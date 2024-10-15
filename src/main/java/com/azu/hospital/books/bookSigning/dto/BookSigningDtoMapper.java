package com.azu.hospital.books.bookSigning.dto;

import com.azu.hospital.books.attachments.dto.AttachmentsDtoMapper;
import com.azu.hospital.books.bookSigning.entity.BookSigning;
import com.azu.hospital.books.copyTo.dto.CopyToDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BookSigningDtoMapper implements Function<BookSigning, BookSigningDto> {

    @Override
    public BookSigningDto apply(BookSigning bookSigning) {
        return new BookSigningDto(
                bookSigning.getId(),
                bookSigning.getUsersToSign() == null ? null : bookSigning.getUsersToSign().getId(),
                bookSigning.getUsersToSign() == null ? null : bookSigning.getUsersToSign().getUsernameSpecial(),
                bookSigning.getSignedUsers() == null ? null : bookSigning.getSignedUsers().getId(),
                bookSigning.getSignedUsers() == null ? null : bookSigning.getSignedUsers().getUsernameSpecial(),
                bookSigning.getState());
    }
}
