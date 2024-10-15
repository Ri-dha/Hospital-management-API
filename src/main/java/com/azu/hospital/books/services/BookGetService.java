package com.azu.hospital.books.services;


import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.books.dao.BookDao;
import com.azu.hospital.books.dto.BookDto;
import com.azu.hospital.books.dto.BookDtoMapper;
import com.azu.hospital.books.entity.Book;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.book.BookState;
import com.azu.hospital.utils.enums.book.BookType;
import com.azu.hospital.utils.enums.book.SigningState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class BookGetService {
    private final BaseUserDao userDao;
    private final ExceptionsMessageReturn messageReturn;

    private final BookDao bookDao;

    private final BookDtoMapper mapper;

    @Autowired
    public BookGetService(BaseUserDao userDao, ExceptionsMessageReturn messageReturn, BookDao bookDao, BookDtoMapper mapper) {
        this.userDao = userDao;
        this.messageReturn = messageReturn;
        this.bookDao = bookDao;
        this.mapper = mapper;
    }


    public List<BookDto> getAllWithFilter(String title, Long ownerId, Long singerId, List<BookState> bookStates, List<BookType> bookTypes) {
        List<Book> books = bookDao.getAllWithFilter(title, ownerId, singerId, bookStates, bookTypes);

        return books.stream()
                .map(mapper)
                .toList();
    }

    public List<BookDto> getAllBooksNeedToSign(Long usersToSignId) {
        SigningState state = SigningState.No_Signature;
        List<Book> books = bookDao.getAllBooksNeedToSign(usersToSignId, state);

        return books.stream()
                .map(mapper)
                .toList();
    }

    public BookDto getBookById(Long bookId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        return bookDao.findBookById(bookId)
                .stream()
                .map(mapper)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getString("bookNotFound") + " " + bookId
                ));


    }

}
