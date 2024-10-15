package com.azu.hospital.lab_collection.blood_bank.BottleOrder.services;

import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.lab_collection.blood_bank.BottleOrder.dao.BottleOrderDao;
import com.azu.hospital.lab_collection.blood_bank.BottleOrder.dto.BottleOrderDto;
import com.azu.hospital.lab_collection.blood_bank.BottleOrder.dto.BottleOrderDtoMapper;
import com.azu.hospital.lab_collection.blood_bank.BottleOrder.entity.BottleOrder;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.bottles.BottleStateEnum;
import com.azu.hospital.utils.enums.bottles.BottleTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BottleOrderGetService {
    private final BottleOrderDao bottleOrderDao;

    private final PatientDao patientDao;
    private final BottleOrderDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public BottleOrderGetService(BottleOrderDao bottleOrderDao,  PatientDao patientDao, BottleOrderDtoMapper mapper, ExceptionsMessageReturn messageReturn) {
        this.bottleOrderDao = bottleOrderDao;
        this.patientDao = patientDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }

    public List<BottleOrderDto> getBloodBankHome() {
        return bottleOrderDao.
                findTopByStateOrderByCreatedAtDesc().
                stream().
                map(mapper::toDto).
                toList();
    }


    public BottleOrderDto getBottleOrderById(Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        return bottleOrderDao.findBottleOrderById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getString("bloodBottleNotFound") + " " + id
                ));
    }




    public Page<BottleOrderDto> getAllArchivedBottleOrder(String patientName, Pageable pageable) {

        Page<BottleOrder> bottleOrders = bottleOrderDao.getAllArchivedBottleOrder(
                BottleStateEnum.Archived,
                patientName,
                pageable

        );

        return new PageImpl<>(bottleOrders.stream()
                .map(mapper::toDto)
                .toList());
    }


    public Page<BottleOrderDto> getNewBottleOrder(
            Boolean isReserved,
            Boolean isPending,
            String search,
            Pageable pageable
    ) {
        Page<BottleOrder> bottleOrders = bottleOrderDao.getNewOrder(
                isPending ? BottleStateEnum.Pending : BottleStateEnum.NoArchive,
                isReserved,
                search,
                pageable

        );
        return new PageImpl<>(
                bottleOrders.stream()
                        .map(mapper::toDto)
                        .toList());
    }

    public Page<BottleOrderDto> getAllNewRequestWithFilter(BottleStateEnum statusEnum,String search, Pageable pageable) {
        Page<BottleOrder> bottleOrders = bottleOrderDao.getAllNewRequestWithFilter(
                statusEnum,
                search,
                pageable

        );
        return new PageImpl<>(
                bottleOrders.stream()
                .map(mapper::toDto)
                .toList());
    }

    public Page<BottleOrderDto> getAllBottleOrdersByTypeAndState(
            BottleTypeEnum bottleType,
            List<BottleStateEnum> states,
            Pageable pageable
    ) {

        Page<BottleOrder> bottleOrders = bottleOrderDao.findByBottleTypeAndStateIn(
                bottleType,
                states,
                pageable
        );

        return new PageImpl<>(
                bottleOrders.getContent().stream()
                        .map(mapper::toDto)
                        .toList(),
                pageable,
                bottleOrders.getTotalElements()
        );
    }


}
