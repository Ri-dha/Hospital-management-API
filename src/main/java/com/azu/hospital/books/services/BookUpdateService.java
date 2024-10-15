package com.azu.hospital.books.services;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.books.dao.BookDao;
import com.azu.hospital.books.entity.Book;
import com.azu.hospital.books.request.UpdateNewBookRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class BookUpdateService {
    private final BookDao dao;
    private final BaseUserDao userDao;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public BookUpdateService(BookDao dao, BaseUserDao userDao, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.userDao = userDao;
        this.messageReturn = messageReturn;
    }

    public void updateBook(Long bookId, UpdateNewBookRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Book book = dao.findBookById(bookId).orElseThrow(
                () -> new RuntimeException(
                        messages.getString("bookNotFound") + " " + bookId
                )
        );

        boolean changes = false;

        if (request.getTitle() != null) {
            book.setTitle(request.getTitle());
            changes = true;
        }
        if (request.getBookBody() != null) {
            book.setBookBody(request.getBookBody());
            changes = true;
        }
        if (request.getDirectorateName() != null) {
            book.setDirectorateName(request.getDirectorateName());
            changes = true;
        }
        if (request.getDirectorateNameTo() != null) {
            book.setDirectorateNameTo(request.getDirectorateNameTo());
            changes = true;
        }
        if (request.getType() != null) {
            book.setType(request.getType());
            changes = true;
        }
        if (request.getImportNumber() != null) {
            book.setImportNumber(request.getImportNumber());
            changes = true;
        }
        if (request.getExportNumber() != null) {
            book.setExportNumber(request.getExportNumber());
            changes = true;
        }
        if (!changes) {
            throw new RuntimeException("No changes found");
        }
        dao.updateBook(book);
    }
}
