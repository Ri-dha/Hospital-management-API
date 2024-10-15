package com.azu.hospital.books.copyTo.service;

import com.azu.hospital.books.copyTo.dao.CopyToDao;
import com.azu.hospital.books.copyTo.dto.CopyToDto;
import com.azu.hospital.books.copyTo.dto.CopyToDtoMapper;
import com.azu.hospital.books.dao.BookDao;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


@Service
public class CopyToGetService {
    private final CopyToDao dao;
    private final BookDao bookDao;

    private final CopyToDtoMapper mapper;

    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public CopyToGetService(CopyToDao dao, BookDao bookDao, CopyToDtoMapper mapper, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.bookDao = bookDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }

    public CopyToDto getCopyToById(Long copyToId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        return dao.getCopyToById(copyToId)
                .stream()
                .map(mapper)
                .findFirst()
                .orElseThrow(
                        () -> new RuntimeException(
                                messages.getString("bookCopyNotFound") + " " + copyToId)
                );
    }

    public List<CopyToDto> getAllByBookId(Long bookId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        bookDao.findBookById(bookId)
                .orElseThrow(
                        () -> new RuntimeException(
                                messages.getString("bookNotFound") + " " + bookId)
                );

        return dao.getAllCopyTo(bookId)
                .stream()
                .map(mapper)
                .toList();
    }


}
