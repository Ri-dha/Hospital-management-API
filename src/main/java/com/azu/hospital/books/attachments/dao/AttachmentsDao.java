package com.azu.hospital.books.attachments.dao;

import com.azu.hospital.books.attachments.entity.Attachments;

import java.util.List;
import java.util.Optional;

public interface AttachmentsDao {

    void createAttachment(Attachments attachment);

    void updateAttachment(Attachments attachment);

    Optional<Attachments> getAttachmentById(Long id);

    List<Attachments> getAttachmentsByBookId(Long bookId);


}
