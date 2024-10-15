package com.azu.hospital.books.request;

import com.azu.hospital.utils.enums.book.BookType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateNewBookRequest {


    private String title;

    private String bookBody;

    private BookType type;

    private String directorateName;

    private String directorateNameTo;

    private String importNumber;

    private String exportNumber;


}
