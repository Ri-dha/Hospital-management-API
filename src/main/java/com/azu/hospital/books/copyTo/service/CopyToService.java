package com.azu.hospital.books.copyTo.service;


import com.azu.hospital.books.copyTo.dao.CopyToDao;
import com.azu.hospital.books.copyTo.entity.CopyTo;
import com.azu.hospital.books.copyTo.request.CopyToRequest;
import com.azu.hospital.books.dao.BookDao;
import com.azu.hospital.books.entity.Book;
import com.azu.hospital.bulding.department.dao.DepartmentDao;
import com.azu.hospital.bulding.department.entity.Department;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class CopyToService {
    private final CopyToDao dao;
    private final BookDao bookDao;
    private final DepartmentDao departmentDao;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public CopyToService(CopyToDao dao, BookDao bookDao, DepartmentDao departmentDao, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.bookDao = bookDao;
        this.departmentDao = departmentDao;
        this.messageReturn = messageReturn;
    }

    public void createCopyTo(Long bookId, Long departmentId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        Book book =bookDao.findBookById(bookId)
                .orElseThrow(
                () -> new RuntimeException(
                        messages.getString("bookNotFound")+" "+ bookId)
                );

        Department department = departmentDao.findDepartmentById(departmentId)
                .orElseThrow(
                        () -> new RuntimeException(
                                messages.getString("departmentNotFound")+" "+ departmentId)
                );
        CopyTo copyTo = new CopyTo();
        copyTo.setDepartment(department);
        copyTo.setBook(book);
        dao.createCopyTo(copyTo);
    }





}
