package com.azu.hospital.ph.Sales.customer.dto;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.Sales.customer.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("customer_dto_mapper")
public class CustomerDtoMapper {
    public CustomerDto toDto(Customer customer) {
        return Optional.ofNullable(customer)
                .map(c -> new CustomerDto(
                        c.getCustomerId(),
                        c.getName(),
                        c.getAge(),
                        c.getGender(),
                        c.getContact(),
                        c.getDoctorName()
                ))
                .orElseThrow(
                        () -> new ResourceNotFoundException("There is no data for returning")
                );
    }
}
