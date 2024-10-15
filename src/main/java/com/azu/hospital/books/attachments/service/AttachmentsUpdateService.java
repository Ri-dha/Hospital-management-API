package com.azu.hospital.books.attachments.service;

import com.azu.hospital.books.attachments.dao.AttachmentsDao;
import com.azu.hospital.books.attachments.entity.Attachments;
import com.azu.hospital.books.attachments.request.AttachmentsRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class AttachmentsUpdateService {
    private final AttachmentsDao dao;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public AttachmentsUpdateService(AttachmentsDao dao, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.messageReturn = messageReturn;
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
}
