package com.azu.hospital.books.copyTo.dao;

import com.azu.hospital.books.copyTo.dto.CopyToDto;
import com.azu.hospital.books.copyTo.entity.CopyTo;

import java.util.List;
import java.util.Optional;

public interface CopyToDao {


    void createCopyTo(CopyTo copyTo);

    void updateCopyTo(CopyTo copyTo);

    Optional<CopyTo> getCopyToById(Long id);

    List<CopyTo> getAllCopyTo(Long bookId);
}
