package com.azu.hospital.bulding.ward.wardFurniture.services;

import com.azu.hospital.bulding.ward.wardFurniture.dao.WardFurnitureDao;
import com.azu.hospital.bulding.ward.wardFurniture.dto.WardFurnitureDto;
import com.azu.hospital.bulding.ward.wardFurniture.dto.WardFurnitureDtoMapper;
import com.azu.hospital.bulding.ward.wardFurniture.entity.WardFurniture;
import com.azu.hospital.bulding.ward.wardFurniture.request.CreateFurnitureDataRequest;
import com.azu.hospital.bulding.ward.wardFurniture.request.FurnitureDataRequest;
import com.azu.hospital.bulding.ward.dao.WardDao;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItemDao;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItems;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.WardInventoryRequestEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class WardFurnitureService {


    private final WardFurnitureDao wardFurnitureDao;
    private final WardDao wardDao;
    private final OtherItemDao otherItemDao;

    private final WardFurnitureDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public WardFurnitureService(
            @Qualifier("wardFurnitureRepo") WardFurnitureDao wardFurnitureDao,
            @Qualifier("wardRepo") WardDao wardDao,
            @Qualifier("otherItemJPA") OtherItemDao otherItemDao,
            WardFurnitureDtoMapper mapper, ExceptionsMessageReturn messageReturn
    ) {
        this.wardFurnitureDao = wardFurnitureDao;
        this.wardDao = wardDao;
        this.otherItemDao = otherItemDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }

    public List<WardFurnitureDto> createNewWardFurniture(CreateFurnitureDataRequest request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Ward ward = wardDao.findWardById(request.getWardId()).orElseThrow(
                ()->new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        List<WardFurniture> furnitures = new ArrayList<WardFurniture>();

        for (FurnitureDataRequest furnitureDataRequest:request.getFurnitures()){

            OtherItems otherItems = otherItemDao.selectById(furnitureDataRequest.getFurnitureId()).orElseThrow(
                    ()->new ResourceNotFoundException(
                            messages.getString("resourceNotFound")
                    )
            );

            if (otherItems.getItemQuantity() < furnitureDataRequest.getCount()){
                throw new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                );
            }

            WardFurniture wardFurniture = new WardFurniture(furnitureDataRequest.getCount() ,
                    WardInventoryRequestEnum.Waitting , furnitureDataRequest.getNote());
            wardFurniture.setWard(ward);
            wardFurniture.setFurniture(otherItems);
            furnitures.add(wardFurniture);
        }

        List<WardFurniture> wardFurnitures = wardFurnitureDao.saveAllFurniture(furnitures);

        ward.setWardFurniture(wardFurnitures);

        return wardFurnitures.stream().map(mapper::toDto).collect(Collectors.toList());

    }


    public List<WardFurnitureDto> getAllWardDeviceByFilter(
            WardInventoryRequestEnum status ,
            String itemName,
            Pageable pageable
    ){

        List<WardInventoryRequestEnum> state = new ArrayList<>();
        if (status == null){
            state = List.of(WardInventoryRequestEnum.values());
        }else {
            state = List.of(status);
            System.out.println(state);
        }

        Page<WardFurniture> wardFurniture = wardFurnitureDao.getAllWardFurnitureByStatusAndItemName(state , itemName , pageable);

        return wardFurniture.stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
