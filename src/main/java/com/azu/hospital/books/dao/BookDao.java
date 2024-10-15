package com.azu.hospital.books.dao;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.books.entity.Book;
import com.azu.hospital.utils.enums.book.BookState;
import com.azu.hospital.utils.enums.book.BookType;
import com.azu.hospital.utils.enums.book.SigningState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    void createNewBook(Book book);

    Optional<Book> findBookById(Long bookId);

    void updateBook(Book book);

    List<Book> getAllWithFilter(String title, Long ownerId, Long singerId , List<BookState> bookStates, List<BookType> bookTypes);//by title

    List<Book> getAllBooksNeedToSign(Long usersToSignId, SigningState signingState);

}
