package com.azu.hospital.bulding.unit.unitFurniture.services;

import com.azu.hospital.bulding.unit.dao.UnitDao;
import com.azu.hospital.bulding.unit.entity.Unit;
import com.azu.hospital.bulding.unit.unitFurniture.dao.UnitFurnitureDao;
import com.azu.hospital.bulding.unit.unitFurniture.dto.UnitFurnitureDto;
import com.azu.hospital.bulding.unit.unitFurniture.dto.UnitFurnitureDtoMapper;
import com.azu.hospital.bulding.unit.unitFurniture.entity.UnitFurniture;
import com.azu.hospital.bulding.unit.unitFurniture.request.CreateFurnitureDataRequest;
import com.azu.hospital.bulding.unit.unitFurniture.request.FurnitureDataRequest;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItemDao;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItems;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
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
public class UnitFurnitureService {


    private final UnitFurnitureDao unitFurnitureDao;
    private final UnitDao unitDao;
    private final OtherItemDao otherItemDao;

    private final UnitFurnitureDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public UnitFurnitureService(
            @Qualifier("unitFurnitureRepo") UnitFurnitureDao unitFurnitureDao,
            @Qualifier("unitRepo") UnitDao unitDao,
            @Qualifier("otherItemJPA") OtherItemDao otherItemDao,
            UnitFurnitureDtoMapper mapper, ExceptionsMessageReturn messageReturn
    ) {
        this.unitFurnitureDao = unitFurnitureDao;
        this.unitDao = unitDao;
        this.otherItemDao = otherItemDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }

    public List<UnitFurnitureDto> createNewUnitFurniture(CreateFurnitureDataRequest request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Unit unit = unitDao.findUnitById(request.getUnitId()).orElseThrow(
                ()->new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        List<UnitFurniture> furnitures = new ArrayList<UnitFurniture>();

        for (FurnitureDataRequest furnitureDataRequest:request.getFurnitures()){

            OtherItems otherItems = otherItemDao.selectById(furnitureDataRequest.getFurnitureId()).orElseThrow(
                    ()->new ResourceNotFoundException(
                            messages.getString("resourceNotFound")
                    )
            );

            if (otherItems.getItemQuantity() < furnitureDataRequest.getCount()){
                throw new ResourceNotFoundException(
                        messages.getString("cannotAccept")
                );
            }

            UnitFurniture unitFurniture = new UnitFurniture(furnitureDataRequest.getCount() ,
                    UnitInventoryRequestEnum.Waitting , furnitureDataRequest.getNote());
            unitFurniture.setUnit(unit);
            unitFurniture.setFurniture(otherItems);
            furnitures.add(unitFurniture);
        }

        List<UnitFurniture> unitFurnitures = unitFurnitureDao.saveAllFurniture(furnitures);

        unit.setUnitFurniture(unitFurnitures);

        return unitFurnitures.stream().map(mapper::toDto).collect(Collectors.toList());

    }


    public List<UnitFurnitureDto> getAllUnitDeviceByFilter(
            UnitInventoryRequestEnum status ,
            String itemName,
            Pageable pageable
    ){

        List<UnitInventoryRequestEnum> state = new ArrayList<>();
        if (status == null){
            state = List.of(UnitInventoryRequestEnum.values());
        }else {
            state = List.of(status);
            System.out.println(state);
        }

        Page<UnitFurniture> unitFurniture = unitFurnitureDao.getAllUnitFurnitureByStatusAndItemName(state , itemName , pageable);

        return unitFurniture.stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
