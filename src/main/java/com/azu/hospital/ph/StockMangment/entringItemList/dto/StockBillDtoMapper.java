package com.azu.hospital.ph.StockMangment.entringItemList.dto;


import com.azu.hospital.ph.StockMangment.Consumbles.Dto.ConsumableForBillDto;
import com.azu.hospital.ph.StockMangment.Consumbles.Dto.SecondConsumableMapper;
import com.azu.hospital.ph.StockMangment.addOtherItems.dto.OtherItemForBillDto;
import com.azu.hospital.ph.StockMangment.addOtherItems.dto.OtherItemFotBillDtoMapper;
import com.azu.hospital.ph.StockMangment.entringItemList.entity.StockBill;
import com.azu.hospital.ph.lab_inventory.lab_main_table.dto.MainLabStoreDtoV2;
import com.azu.hospital.ph.lab_inventory.lab_main_table.dto.MainLabStoreDtoV2Mapper;
import com.azu.hospital.ph.lab_inventory.lab_main_table.dto.MainLabTubeStoreDto;
import com.azu.hospital.ph.lab_inventory.lab_main_table.dto.MainLabTubeStoreDtoMapper;
import com.azu.hospital.ph.main_data_store.drugs_item.dto.DrugsItemForBillDto;
import com.azu.hospital.ph.main_data_store.drugs_item.dto.DrugsItemForBillDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StockBillDtoMapper implements Function<StockBill, StockBillDto> {

    private final DrugsItemForBillDtoMapper mapper;
    private final SecondConsumableMapper consumableMapper;
    private final OtherItemFotBillDtoMapper otherItemFotBillDtoMapper;
    private final MainLabStoreDtoV2Mapper labTubeStoreDtoMapper;

    @Autowired
    public StockBillDtoMapper(DrugsItemForBillDtoMapper mapper, SecondConsumableMapper consumableMapper,
                              OtherItemFotBillDtoMapper otherItemFotBillDtoMapper, MainLabStoreDtoV2Mapper labTubeStoreDtoMapper) {
        this.mapper = mapper;
        this.consumableMapper = consumableMapper;
        this.otherItemFotBillDtoMapper = otherItemFotBillDtoMapper;
        this.labTubeStoreDtoMapper = labTubeStoreDtoMapper;
    }

    @Override
    public StockBillDto apply(StockBill stockBill) {


        List<DrugsItemForBillDto> drugs = stockBill.getDrugs()
                .stream()
                .map(mapper)
                .toList();
        List<DrugsItemForBillDto> mappedDrugs = mapDrugDtoList(drugs);

        List<ConsumableForBillDto> consumables = stockBill.getConsumables()
                .stream()
                .map(consumableMapper)
                .toList();
        List<ConsumableForBillDto> mappedConsumables = mapConsumablesList(consumables);

        List<OtherItemForBillDto> items = stockBill.getItems()
                .stream()
                .map(otherItemFotBillDtoMapper)
                .toList();
        List<OtherItemForBillDto> mappedItemList = mapItemsList(items);

        List<MainLabStoreDtoV2> mainLabTubeStores = stockBill.getMainLabTubeStores()
                .stream()
                .map(labTubeStoreDtoMapper)
                .toList();
        List<MainLabStoreDtoV2> mappedMainLabTubeStores = mapMainLabTubeStores(mainLabTubeStores);

        return StockBillDto.builder()
                .billId(stockBill.getBillId())
                .pharmacyName(stockBill.getPharmacyName())
                .pharmacy(stockBill.getPharmacyName())
                .supplierDetails(stockBill.getSupplierDetails())
                .billNumber(stockBill.getBillNumber())
                .billDate(stockBill.getBillDate())
                .billEntryDate(stockBill.getBillEntryDate())
                .billTotalMoneyPaid(stockBill.getBillTotalMoneyPaid())
                .billTotalPrice(stockBill.getBillTotalPrice())
                .billTotalDiscount(stockBill.getBillTotalDiscount())
                .billTotalDebts(stockBill.getBillTotalDebts())
                .BillTotalAfterDiscount(stockBill.getBillTotalAfterDiscount())
                .totalRestMoney(stockBill.getTotalRestMoney())
                .billDescriptions(stockBill.getBillDescriptions())
                .state(stockBill.getBillState())
                .drugs(mappedDrugs)
                .consumables(mappedConsumables)
                .items(mappedItemList)
                .mainLabTubeStores(mappedMainLabTubeStores)
                .mobile(stockBill.getMobile())
                .location(stockBill.getLocation())
                .creatAt(stockBill.getCreateAt())
                .updateAt(stockBill.getUpdateAt())
                .build();

    }

    private List<DrugsItemForBillDto> mapDrugDtoList(List<DrugsItemForBillDto> drugs) {
        return drugs
                .stream()
                .map(drugDto -> new DrugsItemForBillDto(
                        drugDto.getDrugId(),
                        drugDto.getDrugTradeName(),
                        drugDto.getQuantity(),
                        drugDto.getDrugBonus(),
                        drugDto.getPrice()
                ))
                .collect(Collectors.toList());
    }

    private List<ConsumableForBillDto> mapConsumablesList(List<ConsumableForBillDto> consumables) {
        return consumables
                .stream()
                .map(consumableDto -> new ConsumableForBillDto(
                        consumableDto.getConsumableId(),
                        consumableDto.getConsumableName(),
                        consumableDto.getQuantity(),
                        consumableDto.getPrice(),
                        consumableDto.getBounce()
                )).collect(Collectors.toList());
    }

    private List<OtherItemForBillDto> mapItemsList(List<OtherItemForBillDto> items) {
        return items
                .stream()
                .map(otherItemDto -> new OtherItemForBillDto(
                        otherItemDto.getItemId(),
                        otherItemDto.getItemName(),
                        otherItemDto.getItemQuantity(),
                        otherItemDto.getBounce(),
                        otherItemDto.getItemBuyingPrice()
                )).collect(Collectors.toList());
    }

    private List<MainLabStoreDtoV2> mapMainLabTubeStores(List<MainLabStoreDtoV2> mainLabTubeStores) {
        return mainLabTubeStores
                .stream()
                .map(MainLabStoreDtoV2 -> new MainLabStoreDtoV2(

                        MainLabStoreDtoV2.getId(),
                        MainLabStoreDtoV2.getTubeName(),
                        MainLabStoreDtoV2.getTubeCompany(),
                        MainLabStoreDtoV2.getQuantity(),
                        MainLabStoreDtoV2.getItemBuyingPrice()

                )).collect(Collectors.toList());
    }
}
