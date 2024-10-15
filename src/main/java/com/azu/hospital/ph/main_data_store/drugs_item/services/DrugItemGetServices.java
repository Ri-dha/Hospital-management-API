package com.azu.hospital.ph.main_data_store.drugs_item.services;

import com.azu.hospital.ph.main_data_store.drugs_item.dao.DrugItemDao;
import com.azu.hospital.ph.main_data_store.drugs_item.dto.DrugIsBackDto;
import com.azu.hospital.ph.main_data_store.drugs_item.dto.DrugIsBackDtoMapper;
import com.azu.hospital.ph.main_data_store.drugs_item.dto.DrugItemDto;
import com.azu.hospital.ph.main_data_store.drugs_item.dto.DrugItemDtoMapper;
import com.azu.hospital.ph.main_data_store.drugs_item.entity.DrugsItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Service
public class DrugItemGetServices {

    private final DrugItemDao dao;
    private final DrugItemDtoMapper dtoMapper;
    private final DrugIsBackDtoMapper drugIsBackDtoMapper;

    public DrugItemGetServices(DrugItemDao dao, DrugItemDtoMapper dtoMapper, DrugIsBackDtoMapper drugIsBackDtoMapper) {
        this.dao = dao;
        this.dtoMapper = dtoMapper;
        this.drugIsBackDtoMapper = drugIsBackDtoMapper;
    }


    public DrugItemDto getByBarcode(String barcode) {
        return dao.findDrugByBarcode(barcode)
                .stream()
                .map(dtoMapper)
                .findFirst()
                .orElse(null);
    }

    public Page<DrugItemDto> getAllDrugs(
            @RequestParam("drugId")  Long drugId,
            @RequestParam("drugTradeName") String drugTradeName,
            @RequestParam("drugScientificName") String drugScientificName,
            @RequestParam("barcode") String barcode,
            Pageable pageable){

        return dao.findAllByDrugAndOrderBy(drugId, drugTradeName, drugScientificName, barcode, pageable)
                .map(dtoMapper);

    }

    public Page<DrugItemDto> getAllExpireDrugs(Pageable pageable) {
        Page<DrugsItem> itemDtoPage = dao.getAllExpireDate(pageable);

        List<DrugItemDto> dtoList = itemDtoPage
                .stream()
                .filter(d -> isExpired(d.getExpDate()))
                .map(dtoMapper)
                .toList();

        return new PageImpl<>(dtoList, pageable, itemDtoPage.getTotalElements());
    }


    private boolean isExpired(String expDateString) {
        LocalDate expDate = LocalDate.parse(expDateString);
        return expDate.isBefore(LocalDate.now()) || expDate.isEqual(LocalDate.now());
    }


    public Page<DrugItemDto> getAllExpireDrugsSpecial(String expDate,Pageable pageable){
        List<DrugItemDto> dtoList =  dao.findExpiredByYearMonthDay(expDate)
                .stream()
                .map(dtoMapper)
                .toList();
        return new PageImpl<>(dtoList, pageable, pageable.getPageSize());
    }

    public Page<DrugIsBackDto> getAllRefundDrugs(Pageable pageable){
        List<DrugIsBackDto> dtoList =  dao.findAllRefundDrugs(true)
                .stream()
                .map(drugIsBackDtoMapper)
                .toList();
        return new PageImpl<>(dtoList, pageable, pageable.getPageSize());
    }


    public DrugItemDto getById(Long id){
        return dao.selectDrudById(id)
                .map(dtoMapper)
                .orElse(null);
    }

    public Page<DrugItemDto> getAllDrugsWithPrice(Pageable pageable){
        return dao.getAllDrugWithPrice(pageable)
                .map(dtoMapper);
    }

}
