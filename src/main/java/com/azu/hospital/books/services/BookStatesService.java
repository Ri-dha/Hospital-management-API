package com.azu.hospital.books.services;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.books.dao.BookDao;
import com.azu.hospital.books.entity.Book;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Dtos.StatusDto;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.book.BookState;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class BookStatesService extends GenericBaseService {
    private final BookDao dao;
    private final BaseUserDao baseUserDao;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public BookStatesService(
            BookDao dao,
            BaseUserDao baseUserDao,
            ExceptionsMessageReturn messageReturn,
            JwtService jwtService,
            HttpServletRequest request

    ) {
        super(jwtService, request);
        this.dao = dao;
        this.baseUserDao = baseUserDao;
        this.messageReturn = messageReturn;
    }

    @Transactional
    public StatusDto<BookState> archiveBook(Long bookId, String state) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Book book = dao.findBookById(bookId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("bookNotFound") + " " + bookId
                )
        );
        if (state.equals("Archive") || state.equals("archive")) {
            book.setState(BookState.Archived);
            dao.updateBook(book);
            return new StatusDto<>(BookState.Archived);
        }

        throw new BadRequestException(
                messages.getString("cannotAccept")
        );
    }


}









