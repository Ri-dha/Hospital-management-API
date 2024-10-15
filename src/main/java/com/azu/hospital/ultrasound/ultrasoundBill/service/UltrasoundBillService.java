package com.azu.hospital.ultrasound.ultrasoundBill.service;


import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.radiology.radiology_bill_handler.entity.RadiologyBillHandler;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.ultrasound.ultrasoundBill.dao.UltrasoundBillDao;
import com.azu.hospital.ultrasound.ultrasoundBill.dto.UltrasoundBillDto;
import com.azu.hospital.ultrasound.ultrasoundBill.dto.UltrasoundBillMapper;
import com.azu.hospital.ultrasound.ultrasoundBill.entity.UltrasoundBill;
import com.azu.hospital.ultrasound.ultrasoundBill.requests.UltrasoundBillCreateRequest;
import com.azu.hospital.ultrasound.ultrasoundBill.requests.UltrasoundUpdateRequest;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class UltrasoundBillService extends GenericBaseService {

    private final UltrasoundBillDao ultrasoundBillDao;
    private final ExceptionsMessageReturn messageReturn;


    private final BaseUserDao userDao;
    private final UltrasoundBillMapper ultrasoundBillMapper;

    public UltrasoundBillService(@Qualifier("UltrasoundBillJpa") UltrasoundBillDao ultrasoundBillDao,
                                 JwtService jwtService,
                                 HttpServletRequest request, ExceptionsMessageReturn messageReturn,
                                 @Qualifier("BaseUserJpa") BaseUserDao userDao,
                                 @Qualifier("ultrasoundBillMapper") UltrasoundBillMapper ultrasoundBillMapper) {
        super(jwtService, request);
        this.ultrasoundBillDao = ultrasoundBillDao;
        this.messageReturn = messageReturn;
        this.userDao = userDao;
        this.ultrasoundBillMapper = ultrasoundBillMapper;
    }



    @Transactional
    public void createUltrasoundBill(UltrasoundBillCreateRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        try {
            UltrasoundBill billHandler = new UltrasoundBill(
                    request.getAccountantNote(),
                    request.getPrice(),
                    request.getType()
            );

            billHandler.setAccountant(userDao.findById(authId()).orElseThrow(
                    ()-> new ResourceNotFoundException(
                            messages.getString("resourceNotFound")
                    )));

            ultrasoundBillDao.createUltrasoundBill(billHandler);

        } catch (PersistenceException ex) {
            throw new DuplicateResourceException(
                    messages.getString("alreadyExist")
            );
        }

    }


    public void updateUltraSoundBill(Long id ,UltrasoundUpdateRequest  request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());

        UltrasoundBill billHandler = ultrasoundBillDao.getUltrasoundBillById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if(request.getNote() != null){
            billHandler.setNote(
                    request.getNote()
            );
        }
        if(request.getPrice() != null){
            billHandler.setPrice(
                    request.getPrice()
            );
        }


        ultrasoundBillDao.updateUltrasoundBill(billHandler);
    }


    public UltrasoundBillDto getUltrasoundBillById(Long id){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        return ultrasoundBillMapper.apply(ultrasoundBillDao.getUltrasoundBillById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        ));
    }

    public List<UltrasoundBillDto> getAllUltrasoundBill(){
        return ultrasoundBillDao.getAllUltrasoundBill().stream().map(ultrasoundBillMapper).toList();
    }

    public UltrasoundBillDto getBillByType(String type){
        UltrasoundBill billHandler = ultrasoundBillDao.findByType(UltrasoundTypeEnum.valueOf(type));
        return ultrasoundBillMapper.apply(billHandler);
    }





}
