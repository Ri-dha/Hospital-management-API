package com.azu.hospital.accounting.add_priceses.services.stateService;

import com.azu.hospital.accounting.add_priceses.dao.PricesDao;
import com.azu.hospital.accounting.add_priceses.entity.Prices;
import com.azu.hospital.accounting.insurance.dao.InsuranceDao;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Dtos.StatusDto;
import com.azu.hospital.utils.enums.operation.OperationStates;
import com.azu.hospital.utils.enums.patient.BillState;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class BillStateService {
    private final InsuranceDao dao;
    private final PricesDao pricesDao;
    private final ExceptionsMessageReturn messageReturn;

    public BillStateService(InsuranceDao dao, PricesDao pricesDao, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.pricesDao = pricesDao;
        this.messageReturn = messageReturn;
    }

    public ObjectNode updateBillState(Long id, String state) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ObjectNode json = JsonNodeFactory.instance.objectNode();

        Prices prices = pricesDao.getItemPriceById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        BillState newBillState;
        switch (state) {
            case "unPaid":
                newBillState = BillState.UNPAID;
                break;
            case "paid":
                newBillState = BillState.PAID;
                break;
            case "midPaid":
                newBillState = BillState.MID_PAID;
                break;
            default:
                throw new BadRequestException(
                        messages.getString("cannotAccept")
                );
        }

        if (prices.getBillState().equals(newBillState)) {
            throw new BadRequestException(
                    messages.getString("alreadyExist")
            );
        }

        prices.setBillState(newBillState);
        pricesDao.updateItemPrices(prices);

        json.put("status", "Bill " + state);

        return json;
    }
}
