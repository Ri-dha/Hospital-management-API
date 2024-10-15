package com.azu.hospital.ecg.ecgbill.service;


import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.ecg.ecgbill.dao.EcgBillDao;
import com.azu.hospital.ecg.ecgbill.dto.EcgBillDto;
import com.azu.hospital.ecg.ecgbill.dto.EcgBillMapper;
import com.azu.hospital.ecg.ecgbill.entity.EcgBill;
import com.azu.hospital.ecg.ecgbill.request.EcgBillCreateRequest;
import com.azu.hospital.ecg.ecgbill.request.EcgUpdateRequest;
import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Generic.GenericBaseService;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class EcgBillService extends GenericBaseService {
    private final ExceptionsMessageReturn messageReturn;

    private final EcgBillDao ecgBillDao;
    private final BaseUserDao userDao;

    private final EcgBillMapper ecgBillMapper;

    @Autowired
    public EcgBillService(JwtService jwtService,
                          HttpServletRequest request, ExceptionsMessageReturn messageReturn,
                          @Qualifier("ecgBillDataJpa") EcgBillDao ecgBillDao,
                          @Qualifier("BaseUserJpa") BaseUserDao userDao,
                          @Qualifier("ecgBillMapper") EcgBillMapper ecgBillMapper) {
        super(jwtService, request);
        this.messageReturn = messageReturn;
        this.ecgBillDao = ecgBillDao;
        this.userDao = userDao;
        this.ecgBillMapper = ecgBillMapper;
    }


    @Transactional
    public void createEcgBill(EcgBillCreateRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        try {
            EcgBill billHandler = new EcgBill(
                    request.getAccountantNote(),
                    request.getPrice()
            );

            billHandler.setAccountant(userDao.findById(authId()).orElseThrow(
                    ()-> new ResourceNotFoundException(
                            messages.getString("resourceNotFound")

                    )));

            ecgBillDao.createEcgBill(billHandler);
        } catch (PersistenceException e) {
            throw new DuplicateResourceException(
                    messages.getString("resourceFound")
            );
        }
    }

    public void updateEcgBill(Long id,EcgUpdateRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        EcgBill ecgBill = ecgBillDao.getExgBillById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                ));

        if (request.getAccountantNote() != null) {
            ecgBill.setNote(
                    request.getAccountantNote()
            );
        }
        if (request.getPrice() != null) {
            ecgBill.setPrice(
                    request.getPrice()
            );
        }

        ecgBillDao.updateEcgBill(ecgBill);

    }


    public List<EcgBillDto> getAllEcgBill() {
        return ecgBillDao.getAllEcgBill().stream().map(ecgBillMapper).toList();
    }


    public EcgBillDto getEcgBillById(Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        return ecgBillDao.getExgBillById(id).map(ecgBillMapper).orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")

                ));
    }



}
