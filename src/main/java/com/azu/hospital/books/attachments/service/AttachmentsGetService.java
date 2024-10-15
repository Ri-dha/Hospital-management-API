package com.azu.hospital.books.attachments.service;


import com.azu.hospital.books.attachments.dao.AttachmentsDao;
import com.azu.hospital.books.attachments.dto.AttachmentsDto;
import com.azu.hospital.books.attachments.dto.AttachmentsDtoMapper;
import com.azu.hospital.books.dao.BookDao;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class AttachmentsGetService {
    private final AttachmentsDao dao;

    private final AttachmentsDtoMapper mapper;

    private final BookDao bookDao;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public AttachmentsGetService(AttachmentsDao dao, AttachmentsDtoMapper mapper, BookDao bookDao, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.mapper = mapper;
        this.bookDao = bookDao;
        this.messageReturn = messageReturn;
    }


    public AttachmentsDto getAttachmentById(Long attachmentId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        return dao.getAttachmentById(attachmentId)
                .stream()
                .map(mapper)
                .findFirst()
                .orElseThrow(
                        () -> new RuntimeException(
                                messages.getString("bookAttatchmentNotFound") + " " + attachmentId)
                );
    }

    public List<AttachmentsDto> getAttachmentsByBookId(Long bookId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        bookDao.findBookById(bookId)
                .orElseThrow(
                        () -> new RuntimeException(
                                messages.getString("bookNotFound") + " " + bookId)
                );
        return dao.getAttachmentsByBookId(bookId)
                .stream()
                .map(mapper)
                .toList();
    }


}
