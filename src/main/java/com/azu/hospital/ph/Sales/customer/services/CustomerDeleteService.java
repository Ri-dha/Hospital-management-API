package com.azu.hospital.ph.Sales.customer.services;

import com.azu.hospital.ph.Sales.customer.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("customer_delete_service")
public class CustomerDeleteService {

    private final CustomerDao customerDao;

    public CustomerDeleteService(
            @Qualifier("customer_dao_repository")
            CustomerDao customerDao
    ) {
        this.customerDao = customerDao;
    }

    public void deleteCustomerById(Long customerId) {
        customerDao.deleteCustomerById(customerId);
    }
}
