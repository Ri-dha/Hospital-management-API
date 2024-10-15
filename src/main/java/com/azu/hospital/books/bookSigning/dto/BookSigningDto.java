package com.azu.hospital.books.bookSigning.dto;

import com.azu.hospital.utils.enums.book.SigningState;

public record BookSigningDto (
        Long id,
        Long userToSignId,
        String userToSignName,
        Long userSignId,
        String userSignerName,
        SigningState state
){

}
