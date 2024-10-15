package com.azu.hospital.ph.Sales.customer.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.Sales.customer.dao.CustomerDao;
import com.azu.hospital.ph.Sales.customer.dto.CustomerDto;
import com.azu.hospital.ph.Sales.customer.dto.CustomerDtoMapper;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service("customer_get_service")
public class CustomerGetService {
    private final CustomerDao customerDao;
    private final CustomerDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;

    public CustomerGetService(
            @Qualifier("customer_dao_repository")
            CustomerDao customerDao,
            @Qualifier("customer_dto_mapper")
            CustomerDtoMapper mapper, ExceptionsMessageReturn messageReturn
    ) {
        this.customerDao = customerDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }

    public CustomerDto getCustomerById(Long customerId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        return customerDao
                .getCustomerById(customerId)
                .map(mapper::toDto)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );
    }

    public Page<CustomerDto> getAllCustomers(Pageable pageable) {
        List<CustomerDto> customers = customerDao
                .getAllCustomer(pageable)
                .stream()
                .map(mapper::toDto)
                .toList();
        return new PageImpl<>(
                customers,
                pageable,
                pageable.getPageNumber()
        );
    }
}
