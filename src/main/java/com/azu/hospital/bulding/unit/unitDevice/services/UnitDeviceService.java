package com.azu.hospital.bulding.unit.unitDevice.services;

import com.azu.hospital.bulding.unit.dao.UnitDao;
import com.azu.hospital.bulding.unit.entity.Unit;
import com.azu.hospital.bulding.unit.unitDevice.dao.UnitDeviceDao;
import com.azu.hospital.bulding.unit.unitDevice.dto.UnitDeviceDto;
import com.azu.hospital.bulding.unit.unitDevice.dto.UnitDeviceDtoMapper;
import com.azu.hospital.bulding.unit.unitDevice.entity.UnitDevice;
import com.azu.hospital.bulding.unit.unitDevice.request.CreateDeviceDataRequest;
import com.azu.hospital.bulding.unit.unitDevice.request.DeviceDataRequest;
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
public class UnitDeviceService {


    private final UnitDeviceDao unitDeviceDao;
    private final UnitDao unitDao;
    private final OtherItemDao otherItemDao;

    private final UnitDeviceDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public UnitDeviceService(
            @Qualifier("unitDeviceRepo") UnitDeviceDao unitDeviceDao,
            @Qualifier("unitRepo") UnitDao unitDao,
            @Qualifier("otherItemJPA") OtherItemDao otherItemDao,
            UnitDeviceDtoMapper mapper, ExceptionsMessageReturn messageReturn
    ) {
        this.unitDeviceDao = unitDeviceDao;
        this.unitDao = unitDao;
        this.otherItemDao = otherItemDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }

    public List<UnitDeviceDto> createNewUnitDevice(CreateDeviceDataRequest request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Unit unit = unitDao.findUnitById(request.getUnitId()).orElseThrow(
                ()->new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        List<UnitDevice> devices = new ArrayList<UnitDevice>();

        for (DeviceDataRequest deviceDataRequest:request.getDevices()){

            OtherItems otherItems = otherItemDao.selectById(deviceDataRequest.getDeviceId()).orElseThrow(
                    ()->new ResourceNotFoundException(
                            messages.getString("resourceNotFound")
                    )
            );

            if (otherItems.getItemQuantity() < deviceDataRequest.getCount()){
                throw new ResourceNotFoundException(
                        messages.getString("cannotAccept")
                );
            }

            UnitDevice unitDevice = new UnitDevice(deviceDataRequest.getCount() , UnitInventoryRequestEnum.Waitting ,
                    deviceDataRequest.getNote());
            unitDevice.setUnit(unit);
            unitDevice.setDevice(otherItems);
            devices.add(unitDevice);
        }

        List<UnitDevice> unitDevices = unitDeviceDao.saveAllDevices(devices);

        unit.setUnitDevices(unitDevices);

        return unitDevices.stream().map(mapper::toDto).collect(Collectors.toList());

    }


    public List<UnitDeviceDto> getAllUnitDeviceByFilter(
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

        Page<UnitDevice> unitDevice = unitDeviceDao.getAllUnitDeviceByStatusAndItemName(state , itemName , pageable);

        return unitDevice.stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
