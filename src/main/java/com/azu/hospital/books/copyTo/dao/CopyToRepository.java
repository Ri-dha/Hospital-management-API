package com.azu.hospital.books.copyTo.dao;

import com.azu.hospital.books.copyTo.entity.CopyTo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CopyToRepository extends JpaRepository<CopyTo, Long>
{

    @Query("SELECT c FROM CopyTo c WHERE c.book.id = :bookId")
    List<CopyTo> getAllByBookId(@Param("bookId") Long bookId);
}
