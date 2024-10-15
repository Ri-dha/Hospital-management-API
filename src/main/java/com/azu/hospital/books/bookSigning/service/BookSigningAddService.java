package com.azu.hospital.books.bookSigning.service;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.books.bookSigning.dao.BookSigningDao;
import com.azu.hospital.books.bookSigning.entity.BookSigning;
import com.azu.hospital.books.bookSigning.request.BookSigningRequest;
import com.azu.hospital.books.dao.BookDao;
import com.azu.hospital.books.entity.Book;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.book.SigningState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class BookSigningAddService {
    private final BookSigningDao dao;
    private final BaseUserDao userDao;
    private final ExceptionsMessageReturn messageReturn;

    private final BookDao bookDao;

    @Autowired
    public BookSigningAddService(BookSigningDao dao, BaseUserDao userDao, ExceptionsMessageReturn messageReturn, BookDao bookDao) {
        this.dao = dao;
        this.userDao = userDao;
        this.messageReturn = messageReturn;
        this.bookDao = bookDao;
    }

    public void addBookSigning(Long bookId, List<BookSigningRequest> requests) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Book book = bookDao.findBookById(bookId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("bookNotFound") + " " + bookId
                )
        );
        List<BookSigning> bookSignings = new ArrayList<>();

        for (BookSigningRequest request : requests) {
            BaseUser user = userDao.findById(request.userId()).orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("userNotFound") + " " + request.userId()
                    )
            );

            BookSigning bookSigning = new BookSigning();
            bookSigning.setBook(book);
            bookSigning.setUsersToSign(user);
            bookSignings.add(bookSigning);
            bookSigning.setState(SigningState.No_Signature);
        }

        dao.createListBookSigning(bookSignings);
    }


}
