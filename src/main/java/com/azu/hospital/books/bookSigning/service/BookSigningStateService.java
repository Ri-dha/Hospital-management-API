package com.azu.hospital.books.bookSigning.service;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.books.bookSigning.dao.BookSigningDao;
import com.azu.hospital.books.bookSigning.entity.BookSigning;
import com.azu.hospital.books.dao.BookDao;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Dtos.StatusDto;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.book.BookState;
import com.azu.hospital.utils.enums.book.SigningState;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class BookSigningStateService extends GenericBaseService {
    private final BookSigningDao dao;
    private final BookDao bookDao;

    private final BaseUserDao userDao;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public BookSigningStateService(BookSigningDao dao,
                                   BookDao bookDao,
                                   BaseUserDao userDao,
                                   ExceptionsMessageReturn messageReturn,
                                   JwtService jwtService,
                                   HttpServletRequest request) {
        super(jwtService, request);
        this.dao = dao;
        this.bookDao = bookDao;
        this.userDao = userDao;
        this.messageReturn = messageReturn;
    }


    public StatusDto<SigningState> signatureOrReject(Long bookSigningId, String state) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        BookSigning bookSigning = dao.getById(bookSigningId).orElseThrow(
                () -> new RuntimeException(
                        messages.getString("bookSignatureNotFound") + " " + bookSigningId
                )
        );

        if ((bookSigning.getState() != SigningState.No_Signature) || (bookSigning.getBook().getState() != BookState.Rejected)) {
            throw new RuntimeException(
                    messages.getString("cannotAccept") + " or " + messages.getString("alreadyExist")
            );
        }
        if (state.equals("Signature") || state.equals("signature")) {
            bookSigning.setState(SigningState.Signature);
            dao.updateBookSigning(bookSigning);
            return new StatusDto<>(SigningState.Signature);
        } else if (state.equals("Reject_Signature") || state.equals("reject_signature")) {
            bookSigning.setState(SigningState.Reject_Signature);
            dao.updateBookSigning(bookSigning);
            bookSigning.getBook().setState(BookState.Rejected);
            bookDao.updateBook(bookSigning.getBook());
            return new StatusDto<>(SigningState.Reject_Signature);
        }
        throw new BadRequestException(
                messages.getString("cannotAccept")
        );
    }
}
