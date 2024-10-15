package com.azu.hospital.lab_collection.blood_bank.BottleOrder.services;

import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.lab_collection.blood_bank.Bottle.dao.BottleDao;
import com.azu.hospital.lab_collection.blood_bank.Bottle.entity.Bottle;
import com.azu.hospital.lab_collection.blood_bank.BottleOrder.request.AcceptOrderInfo;
import com.azu.hospital.lab_collection.blood_bank.BottleOrder.request.AcceptOrderInfoData;
import com.azu.hospital.lab_collection.blood_bank.BottleOrder.dao.BottleOrderDao;
import com.azu.hospital.lab_collection.blood_bank.BottleOrder.dto.BottleOrderDto;
import com.azu.hospital.lab_collection.blood_bank.BottleOrder.dto.BottleOrderDtoMapper;
import com.azu.hospital.lab_collection.blood_bank.BottleOrder.entity.BottleOrder;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.bottles.BottleStateEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class BottleOrderService {

    private final BottleOrderDao bottleOrderDao;
    private final BottleDao bottleDao;
    private final BottleOrderDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;


    public BottleOrderService(
            @Qualifier("bottleOrderRepo") BottleOrderDao bottleOrderDao,
            @Qualifier("bottleRepo") BottleDao bottleDao, BottleOrderDtoMapper mapper,
            ExceptionsMessageReturn messageReturn
    ) {
        this.bottleOrderDao = bottleOrderDao;
        this.bottleDao = bottleDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }


    public List<BottleOrderDto> getOrderInfoByOrderId(Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        BottleOrder bottleOrder = bottleOrderDao.findBottleOrderById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("bloodBottleNotFound") + " " + id
                )
        );

        return bottleOrderDao.
                findBottleOrderById(id).
                stream().
                map(mapper::toDto).
                collect(Collectors.toList());
    }


    @Transactional
    public void acceptOrder(AcceptOrderInfoData request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        List<BottleOrder> bottleOrders = new ArrayList<>();

            BottleOrder bottleOrder =
                    bottleOrderDao.findBottleOrderInfoById(request.bottleOrderInfoId()).orElseThrow(
                            () -> new ResourceNotFoundException(
                                    messages.getString("bloodBottleOrderNotFound") + " " + request.bottleOrderInfoId()
                            )
                    );
            if (bottleOrder.getState() != BottleStateEnum.NoArchive){
                throw new BadRequestException(
                        messages.getString("alreadyExist")
                );
            }
            List<Bottle> bottles = bottleDao.getAllByManyId(request.bottlesId());

            if (bottles.size() != bottleOrder.getCount() ){
                if (bottles.size() < request.bottlesId().size()){
                    throw new BadRequestException(
                            messages.getString("cannotAccept") + " " + bottles.size()
                    );
                }
                throw new BadRequestException(
                      messages.getString("cannotAccept") + " " + bottleOrder.getCount()
                );
            }
            bottleOrder.setBottles(bottles);
            bottleOrder.setTime(request.time());
            bottleOrders.add(bottleOrder);

            for (Bottle bottle : bottles) {
                bottle.setState(
                        bottleOrder.getIsReserved() ?
                                BottleStateEnum.WaittingDoctorAccept : BottleStateEnum.Preparing
                        );
                bottle.setBottleOrder(bottleOrder);
            }
        bottleOrder.setState(bottles.get(0).getState());
        bottleOrderDao.updateManyBottleOrder(bottleOrders);
        }
    }

