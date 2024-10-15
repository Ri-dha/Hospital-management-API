package com.azu.hospital.books.bookSigning.dao;

import com.azu.hospital.books.bookSigning.entity.BookSigning;

import java.util.List;
import java.util.Optional;

public interface BookSigningDao {

    void createListBookSigning(List<BookSigning> bookSigningList);

    Optional<BookSigning> getById(Long id);

    void updateBookSigning(BookSigning bookSigning);

}
