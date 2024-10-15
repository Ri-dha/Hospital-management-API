package com.azu.hospital.ph.Sales.customer.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.Sales.customer.dao.CustomerDao;
import com.azu.hospital.ph.Sales.customer.entity.Customer;
import com.azu.hospital.ph.Sales.customer.request.CustomerUpdateRequest;
import com.azu.hospital.ph.Sales.customer.utils.CustomerRequestToEntity;
import com.azu.hospital.ph.Sales.dao.SoldBillDao;
import com.azu.hospital.ph.Sales.entity.SoldBill;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service("customer_add_service")
public class CustomerAddService {
    private final CustomerDao customerDao;
    private final SoldBillDao soldBillDao;
    private final ExceptionsMessageReturn messageReturn;

    public CustomerAddService(
            @Qualifier("customer_dao_repository")
            CustomerDao customerDao,
            @Qualifier("soldJpa") SoldBillDao soldBillDao, ExceptionsMessageReturn messageReturn) {
        this.customerDao = customerDao;
        this.soldBillDao = soldBillDao;
        this.messageReturn = messageReturn;
    }

    public void createCustomer(Long billId, CustomerUpdateRequest request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        SoldBill bill = soldBillDao.getBillById(billId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );
        Customer customer = CustomerRequestToEntity.toEntity(request);
        customerDao.createCustomer(customer);
    }
}
