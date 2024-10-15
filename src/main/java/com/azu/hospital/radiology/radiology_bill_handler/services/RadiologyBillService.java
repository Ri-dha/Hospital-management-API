package com.azu.hospital.radiology.radiology_bill_handler.services;


import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.radiology.radiology_bill_handler.dao.RadiologyBillDao;
import com.azu.hospital.radiology.radiology_bill_handler.dto.RadiologyBillDto;
import com.azu.hospital.radiology.radiology_bill_handler.dto.RadiologyBillMapper;
import com.azu.hospital.radiology.radiology_bill_handler.entity.RadiologyBillHandler;
import com.azu.hospital.radiology.radiology_bill_handler.requests.RadiologyCreateRequest;
import com.azu.hospital.radiology.radiology_bill_handler.requests.RadiologyUpdateRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class RadiologyBillService {

    private final RadiologyBillDao radiologyBillDao;
    private final ExceptionsMessageReturn messageReturn;

    private final BaseUserDao userDao;

    private final RadiologyBillMapper radiologyBillMapper;

    @Autowired
    public RadiologyBillService(@Qualifier("RadiologyBillJpa") RadiologyBillDao radiologyBillDao, ExceptionsMessageReturn messageReturn,
                                @Qualifier("BaseUserJpa") BaseUserDao userDao,
                                @Qualifier("radiologyBillMapper") RadiologyBillMapper radiologyBillMapper) {
        this.radiologyBillDao = radiologyBillDao;
        this.messageReturn = messageReturn;
        this.userDao = userDao;
        this.radiologyBillMapper = radiologyBillMapper;
    }


    @Transactional
    public RadiologyBillDto createRadiologyBill(RadiologyCreateRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        try {
            RadiologyBillHandler billHandler = new RadiologyBillHandler(
                    request.getNote(),
                    request.getPrice(),
                    request.getType()
            );

            radiologyBillDao.createRadiologyBill(billHandler);

            return radiologyBillMapper.apply(billHandler);
        } catch (PersistenceException ex) {
            throw new DuplicateResourceException(
                    messages.getString("alreadyExist")
            );
        }
    }


    public RadiologyBillDto updateRadiologyBill(Long id ,RadiologyUpdateRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        RadiologyBillHandler billHandler = radiologyBillDao.getRadiologyBillById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if (request.getNote() != null) {
            billHandler.setNote(
                    request.getNote()
            );
        }

        if (request.getPrice() != null) {
            billHandler.setPrice(
                    request.getPrice()
            );
        }
        radiologyBillDao.updateRadiologyBill(billHandler);

        return radiologyBillMapper.apply(billHandler);

    }

    public RadiologyBillDto getRadiologyBillById(Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        RadiologyBillHandler billHandler = radiologyBillDao.getRadiologyBillById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        return radiologyBillMapper.apply(billHandler);
    }

    public List<RadiologyBillDto> getAllRadiologyBill() {
        return radiologyBillDao.getAllRadiologyBill().stream().map(radiologyBillMapper).toList();
    }

    public RadiologyBillDto getRadiologyBillByType(String type) {
        RadiologyBillHandler billHandler = radiologyBillDao.findByType(RadiologyTypeEnum.valueOf(type));
        return radiologyBillMapper.apply(billHandler);
    }

    public Page<RadiologyBillHandler> getAllWithPrice(Pageable page) {
        return radiologyBillDao.getAllWithPrice(page);
    }
}
