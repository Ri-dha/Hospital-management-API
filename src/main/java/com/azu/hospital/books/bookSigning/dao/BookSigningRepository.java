package com.azu.hospital.books.bookSigning.dao;

import com.azu.hospital.books.bookSigning.entity.BookSigning;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookSigningRepository extends JpaRepository<BookSigning, Long> {

}
