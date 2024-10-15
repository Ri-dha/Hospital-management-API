package com.azu.hospital.ph.Sales.customer.services;

import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.Sales.customer.dao.CustomerDao;
import com.azu.hospital.ph.Sales.customer.entity.Customer;
import com.azu.hospital.ph.Sales.customer.request.CustomerUpdateRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service("customer_update_service")
public class CustomerUpdateService {
    private final ExceptionsMessageReturn messageReturn;

    private final CustomerDao customerDao;

    public CustomerUpdateService(
            ExceptionsMessageReturn messageReturn, @Qualifier("customer_dao_repository")
            CustomerDao customerDao
    ) {
        this.messageReturn = messageReturn;
        this.customerDao = customerDao;
    }

    public void updateCustomer(CustomerUpdateRequest request, Long customerId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        Customer customer = customerDao.getCustomerById(customerId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );

        boolean changes = false;

        if(request.name() != null){
            customer.setName(request.name());
            changes = true;
        }
        if(request.age() != null){
            customer.setAge(request.age());
            changes = true;
        }
        if(request.gender() != null){
            customer.setGender(request.gender());
            changes = true;
        }
        if(request.contact() != null){
            customer.setContact(request.contact());
            changes = true;
        }
        if(request.doctorName() != null){
            customer.setDoctorName(request.doctorName());
            changes = true;
        }

        if(!changes){
            throw new BadRequestException(
                    messages.getString("nocChanges")
            );
        }

        customerDao.updateCustomer(customer);

    }
}
