package com.azu.hospital.books.attachments.dao;


import com.azu.hospital.books.attachments.entity.Attachments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("attachmentsJpa")
public class AttachmentsDataAccessJpa implements AttachmentsDao{

    private final AttachmentsRepository repository;


    @Autowired
    public AttachmentsDataAccessJpa(AttachmentsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createAttachment(Attachments attachment) {
        repository.save(attachment);
    }

    @Override
    public void updateAttachment(Attachments attachment) {
        repository.save(attachment);
    }

    @Override
    public Optional<Attachments> getAttachmentById(Long id) {
        return null;
    }


    @Override
    public List<Attachments> getAttachmentsByBookId(Long bookId) {
        return null;
    }


}
