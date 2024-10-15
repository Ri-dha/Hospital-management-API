package com.azu.hospital.books.copyTo.dao;

import com.azu.hospital.books.copyTo.dto.CopyToDto;
import com.azu.hospital.books.copyTo.entity.CopyTo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("copyToJpa")
public class CopyToDataAccessJpa implements CopyToDao{
    private final CopyToRepository repository;

    public CopyToDataAccessJpa(CopyToRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createCopyTo(CopyTo copyTo) {
        repository.save(copyTo);
    }

    @Override
    public void updateCopyTo(CopyTo copyTo) {
        repository.save(copyTo);
    }

    @Override
    public Optional<CopyTo> getCopyToById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<CopyTo> getAllCopyTo(Long bookId) {
        return repository.getAllByBookId(bookId);
    }
}
