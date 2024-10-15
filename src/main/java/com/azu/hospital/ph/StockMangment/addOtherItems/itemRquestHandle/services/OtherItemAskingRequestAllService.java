package com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.services;

import com.azu.hospital.all_user_sevices.user_dao.UserDao;
import com.azu.hospital.all_user_sevices.user_entity.User;
import com.azu.hospital.exceptions.RequestValidationException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.dao.DeviceExpanseDao;
import com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.dao.OtherItemAskingRequestDao;
import com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.dto.OtherItemAskingRequestDto;
import com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.dto.OtherItemAskingRequestDtoMapper;
import com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.entity.OtherItemAskingRequest;
import com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.requests.OtherItemAskingRegistrationRequest;
import com.azu.hospital.ph.StockMangment.addOtherItems.orders.dao.DeviceExistDao;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class OtherItemAskingRequestAllService {

    private final OtherItemAskingRequestDao dao;
    private final DeviceExistDao existDao;
    private final UserDao userDao;
    private final DeviceExpanseDao expanseDao;
    private final OtherItemAskingRequestDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;

    public OtherItemAskingRequestAllService(
            @Qualifier("AskingRequest") OtherItemAskingRequestDao dao,
            @Qualifier("DeviceExistJpa") DeviceExistDao existDao,
            @Qualifier("UserJpa") UserDao userDao,
            @Qualifier("DeviceExpanseJpa") DeviceExpanseDao expanseDao, OtherItemAskingRequestDtoMapper mapper, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.existDao = existDao;
        this.userDao = userDao;
        this.expanseDao = expanseDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }

    public void updateExistingRequest(Long requestId, OtherItemAskingRegistrationRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        OtherItemAskingRequest existAskingRequest = dao.getRequestById(requestId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound") + requestId
                        )
                );

        if (existAskingRequest.getRequestStatus() == UnitInventoryRequestEnum.Waitting) {

            if (request != null) {
                boolean existing = false;
                if (existAskingRequest.getDevicePlace() != null) {
                    existAskingRequest.setDevicePlace(request.itemPlace());
                    existing = true;
                }if (existAskingRequest.getItemId() != null) {
                    existAskingRequest.setItemId(request.ItemId());
                    existing = true;
                }if (existAskingRequest.getSignature() != null) {
                    User user = userDao.findUserById(request.userId())
                            .orElseThrow(
                                    () -> new ResourceNotFoundException(
                                            messages.getString("userNotFound") + request.userId()
                                    )
                            );
                    existAskingRequest.setSignature(user);
                    existing = true;
                }if (existAskingRequest.getQuantity() != null) {
                    existAskingRequest.setQuantity(request.quantity());
                    existing = true;
                }if (!existing){
                    throw new RequestValidationException(
                            messages.getString("noChanges")
                    );
                }
            }
        }if (existAskingRequest.getRequestStatus() != UnitInventoryRequestEnum.Waitting){
            throw new RequestValidationException(
                    messages.getString("alreadyChanged")
            );
        }
    }


    public List<OtherItemAskingRequestDto> getOtherItemAskingRequestById(Long requestId){
        return dao.getRequestById(requestId)
                .stream()
                .map(mapper)
                .toList();
    }

    public Page<OtherItemAskingRequestDto> getAllRequest(Pageable pageable){
        Page<OtherItemAskingRequest> entitiesPage = dao.getAllRequests(pageable);
        List<OtherItemAskingRequestDto> dtoList = entitiesPage.getContent()
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, entitiesPage.getTotalElements());
    }






}
