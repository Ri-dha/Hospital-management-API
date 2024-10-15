package com.azu.hospital.books.dao;

import com.azu.hospital.books.entity.Book;
import com.azu.hospital.utils.enums.book.BookState;
import com.azu.hospital.utils.enums.book.BookType;
import com.azu.hospital.utils.enums.book.SigningState;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b LEFT JOIN b.bookSignings bs WHERE " +
            "(:title IS NULL OR b.title LIKE concat('%', lower(:title), '%' )) AND " +
            "(b.bookOwner.id = :ownerId OR :ownerId IS NULL) OR " +
            "(bs.id = :singerId OR :singerId IS NULL) OR " +
            "(b.state IN :bookStates OR :bookStates IS NULL) OR " +
            "(b.type IN :bookTypes OR :bookTypes IS NULL)")
    List<Book> getAllWithFilter(
            @Param("title") String title,
            @Param("ownerId") Long ownerId,
            @Param("singerId") Long singerId,
            @Param("bookStates") List<BookState> bookStates ,
            @Param("bookTypes") List<BookType> bookTypes);

    @Query("SELECT b FROM Book b left join b.bookSignings bs WHERE " +
            "(:usersToSignId is null or bs.usersToSign.id = :usersToSignId) and " +
            "(:signingState is null or bs.state = :signingState)")
    List<Book> getAllBooksNeedToSign(Long usersToSignId, SigningState signingState);
}
