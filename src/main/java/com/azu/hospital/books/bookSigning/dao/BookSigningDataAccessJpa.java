package com.azu.hospital.books.bookSigning.dao;


import com.azu.hospital.books.bookSigning.entity.BookSigning;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("bookSigningJpa")
public class BookSigningDataAccessJpa implements BookSigningDao {
    private final BookSigningRepository repository;

    public BookSigningDataAccessJpa(BookSigningRepository repository) {
        this.repository = repository;
    }


    @Override
    public void createListBookSigning(List<BookSigning> bookSigningList) {
        repository.saveAll(bookSigningList);
    }

    @Override
    public Optional<BookSigning> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void updateBookSigning(BookSigning bookSigning) {
        repository.save(bookSigning);
    }


}
