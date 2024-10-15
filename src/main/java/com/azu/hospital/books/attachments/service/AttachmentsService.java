package com.azu.hospital.books.attachments.service;


import com.azu.hospital.books.attachments.dao.AttachmentsDao;
import com.azu.hospital.books.attachments.dto.AttachmentsDtoMapper;
import com.azu.hospital.books.attachments.entity.Attachments;
import com.azu.hospital.books.attachments.request.AttachmentsRequest;
import com.azu.hospital.books.dao.BookDao;
import com.azu.hospital.books.entity.Book;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.files.File;
import com.azu.hospital.utils.files.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class AttachmentsService {

    private final AttachmentsDao dao;

    private final AttachmentsDtoMapper mapper;
    private final FileService fileService;

    private final BookDao bookDao;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public AttachmentsService(AttachmentsDao dao, AttachmentsDtoMapper mapper, FileService fileService, BookDao bookDao, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.mapper = mapper;
        this.fileService = fileService;
        this.bookDao = bookDao;
        this.messageReturn = messageReturn;
    }

    public void createAttachment(Long bookId, AttachmentsRequest request) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Book book = bookDao.findBookById(bookId)
                .orElseThrow(
                        () -> new RuntimeException(
                                messages.getString("bookNotFound") + " " + bookId)
                );

        Attachments attachments = new Attachments(
                request.name()

        );
        List<File> files = uploadMultiFile(request.files());
        attachments.setFiles(files);
        attachments.setBook(book);
        dao.createAttachment(attachments);

    }


    public void updateAttachment(Long attachmentId, AttachmentsRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Attachments attachments = dao.getAttachmentById(attachmentId)
                .orElseThrow(
                        () -> new RuntimeException(
                                messages.getString("bookAttatchmentNotFound") + " " + attachmentId)
                );
        attachments.setName(request.name());
        dao.updateAttachment(attachments);
    }


    private List<File> uploadMultiFile(List<MultipartFile> files) throws IOException {
        List<File> fileList = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String url = fileService.saveFile(file);
                File newFile = new File(file.getContentType(), "Attachments", url);
                fileList.add(newFile);
            }
        }
        return fileList;
    }

}
