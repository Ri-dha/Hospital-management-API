package com.azu.hospital.ph.StockMangment.Consumbles.existbill;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.ConsumablesRequestTableList;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.dao.ConsumablesRequestTableListDao;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.entity.ConsumablesRequestTable;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class PrintingBillServices extends GenericBaseService {
    private final ConsumablesRequestTableListDao consumablesRequestTableDao;
    private final BaseUserDao baseUserDao;


    @Autowired
    public PrintingBillServices(ConsumablesRequestTableListDao consumablesRequestTableDao, BaseUserDao baseUserDao, JwtService jwtService,
                                HttpServletRequest httpServletRequest) {
        super(jwtService, httpServletRequest);
        this.consumablesRequestTableDao = consumablesRequestTableDao;
        this.baseUserDao = baseUserDao;
    }

    @Value("${hospital.name}")
    private String hospitalName;

    @Value("${hospital.store.name}")
    private String hospitalStoreName;

    public PrintingBill printingBill(Long requestId) {
        ConsumablesRequestTableList consumablesRequestTableList = consumablesRequestTableDao.getConsumablesRequestTableListById(requestId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Request with id " + requestId + " does not exist")
                );

        if (consumablesRequestTableList.getRequestListState().equals(UnitInventoryRequestEnum.Accepted)) {
            PrintingBill bill = new PrintingBill();
            bill.setBillNo("00" + requestId.toString());
            bill.setHospitalName(hospitalName);
            if (consumablesRequestTableList.getWard() != null) {
                bill.setToStore(consumablesRequestTableList.getWard().getName());
            } else {
                bill.setToStore(consumablesRequestTableList.getUnit().getName());
            }
            bill.setFromStore(hospitalStoreName);
            bill.setCreatedDate(consumablesRequestTableList.getCreateAt());
            bill.setCreatedBy(providerName());
            bill.setRequestAskBy(getProviderName(consumablesRequestTableList.getCreatedBy()));
//            int quantity = consumablesRequestTableList.getQuantity();
            List<PrintingBillItemList> itemLists = new ArrayList<>();
            for (ConsumablesRequestTable requestTable: consumablesRequestTableList.getConsumablesRequestTables()){
                if (requestTable.getRequestStatus().equals(UnitInventoryRequestEnum.Accepted)){
                PrintingBillItemList billItem = new PrintingBillItemList();
                billItem.setItemName(requestTable.getConsumableName());
                billItem.setCount(requestTable.getQuantity());
                itemLists.add(billItem);}
            }
            bill.setItemLists(itemLists);
            return bill;
        } else {
            throw new ResourceNotFoundException("Request with id " + requestId + " is not received yet");
        }

    }

    private String providerName() {
        return baseUserDao.findById(authId()).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + authId() + " does not exist")
        ).getUsernameSpecial();
    }

    private String getProviderName(Long id) {
        return baseUserDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + id + " does not exist")
        ).getUsernameSpecial();
    }
}