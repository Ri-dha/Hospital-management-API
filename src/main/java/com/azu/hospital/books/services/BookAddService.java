package com.azu.hospital.books.services;

import com.azu.hospital.all_user_sevices.user_dao.UserDao;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.books.dao.BookDao;
import com.azu.hospital.books.dto.BookDtoMapper;
import com.azu.hospital.books.entity.Book;
import com.azu.hospital.books.request.CreateNewBookRequest;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.hospital_info.dao.HospitalInfoDao;
import com.azu.hospital.hospital_info.entity.HospitalInfo;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.book.BookState;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class BookAddService extends GenericBaseService {

    private final BookDao bookDao;
    private final UserDao userDao;
    private final BookDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;

    private final HospitalInfoDao hospitalInfoDao;



    @Autowired
    public BookAddService(
            @Qualifier("bookJpa") BookDao bookDao,
            @Qualifier("UserJpa") UserDao userDao,
            BookDtoMapper mapper, ExceptionsMessageReturn messageReturn,
            JwtService jwtService,
            HttpServletRequest httpServletRequest, HospitalInfoDao hospitalInfoDao
    ) {
        super(jwtService, httpServletRequest);
        this.bookDao = bookDao;
        this.userDao = userDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
        this.hospitalInfoDao = hospitalInfoDao;
    }


    public void createNewBook(CreateNewBookRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        BaseUser user = userDao.findUserById(authId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("userNotFound")+ " " + authId()
                )
        );
        HospitalInfo hospitalInfo = hospitalInfoDao.getHospitalInfoById(1L).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("hospitalInfoNotFound")
                )
        );
        Book book = new Book(
                request.getTitle(),
                request.getBookBody(),
                BookState.Waiting,
                request.getDirectorateName(),
                request.getDirectorateNameTo()
        );
        book.setBookOwner(user);
        book.setHospitalInfo(hospitalInfo);
        bookDao.createNewBook(book);
    }
}
