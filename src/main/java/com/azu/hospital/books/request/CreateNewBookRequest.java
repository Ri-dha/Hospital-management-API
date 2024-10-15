package com.azu.hospital.books.request;

import com.azu.hospital.utils.enums.book.BookType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateNewBookRequest {

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String bookBody;


    private String directorateName;

    private String directorateNameTo;


    public CreateNewBookRequest() {
    }
}
