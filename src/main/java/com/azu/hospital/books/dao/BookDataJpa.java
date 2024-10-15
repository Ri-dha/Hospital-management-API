package com.azu.hospital.books.dao;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.books.entity.Book;
import com.azu.hospital.utils.enums.book.BookState;
import com.azu.hospital.utils.enums.book.BookType;
import com.azu.hospital.utils.enums.book.SigningState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("bookJpa")
public class BookDataJpa implements BookDao{

    private final BookRepository bookRepository;

    @Autowired
    public BookDataJpa(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void createNewBook(Book book) {
         bookRepository.save(book);
    }


    @Override
    public Optional<Book> findBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }



    @Override
    public List<Book> getAllWithFilter(String title, Long ownerId, Long singerId, List<BookState> bookStates, List<BookType> bookTypes) {
        return bookRepository.getAllWithFilter(title, ownerId, singerId, bookStates, bookTypes);
    }

    @Override
    public List<Book> getAllBooksNeedToSign(Long usersToSignId, SigningState signingState) {
        return bookRepository.getAllBooksNeedToSign(usersToSignId, signingState);
    }


}
