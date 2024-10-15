package com.azu.hospital.ph.Sales.solid_item_list.services;

import com.azu.hospital.accounting.all_item_expanse.drugs.dao.PatientDrugsExpanseResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.drugs.entity.PatientDrugsExpanseResultTable;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.Sales.dao.SoldBillDao;
import com.azu.hospital.ph.Sales.entity.SoldBill;
import com.azu.hospital.ph.Sales.solid_item_list.request.SoldItemsUpdateRequest;
import com.azu.hospital.ph.Sales.solid_item_list.dao.SoldItemDao;
import com.azu.hospital.ph.Sales.solid_item_list.entity.SoldItems;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.dao.DrugRequestHandlerListDao;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.entity.DrugRequestHandlerList;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class SoldItemAddServices {

    private final SoldItemDao soldItemDao;
    private final SoldBillDao soldBillDao;
    private final ExceptionsMessageReturn messageReturn;
    private final DrugRequestHandlerListDao requestHandlerListDao;
    private final PatientDrugsExpanseResultTableDao tableDao;

    @Autowired
    public SoldItemAddServices(@Qualifier("SoldItemJpa") SoldItemDao soldItemDao,
                               @Qualifier("soldJpa") SoldBillDao soldBillDao,
                               ExceptionsMessageReturn messageReturn, DrugRequestHandlerListDao requestHandlerListDao, PatientDrugsExpanseResultTableDao tableDao) {
        this.soldItemDao = soldItemDao;
        this.soldBillDao = soldBillDao;

        this.messageReturn = messageReturn;
        this.requestHandlerListDao = requestHandlerListDao;
        this.tableDao = tableDao;
    }


    @Transactional
    public void addItemList(Long billId, Long requestId, @RequestBody List<SoldItemsUpdateRequest> requestList) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        SoldBill bill = soldBillDao.getBillById(billId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound") + billId
                ));

        DrugRequestHandlerList drugRequestHandlerList = requestHandlerListDao.getDrugRequestHandlerListById(requestId).orElseThrow();
        drugRequestHandlerList.setCompleted(true);
        requestHandlerListDao.updateDrugRequestHandlerList(drugRequestHandlerList);
        List<SoldItems> itemsList = new ArrayList<>();


        for (SoldItemsUpdateRequest request : requestList) {
            SoldItems soldItem = createSoldItem(request, bill);
            itemsList.add(soldItem);
        }
        soldItemDao.addItemList(itemsList);

        PatientDrugsExpanseResultTable table = new PatientDrugsExpanseResultTable();
        table.setPatient(bill.getPatient());
        table.setFullPrices(bill.getBillTotalPrice());
        tableDao.createPatientDrugsExpanseResultTable(table);
    }


    private SoldItems createSoldItem(SoldItemsUpdateRequest request, SoldBill bill) {
        SoldItems soldItem = new SoldItems(
                request.drugTradeName(),
                request.quantity()
        );
        soldItem.setSoldBill(bill);
        return soldItem;
    }


}
