package com.azu.hospital.ph.Sales.customer.utils;

import com.azu.hospital.ph.Sales.customer.entity.Customer;
import com.azu.hospital.ph.Sales.customer.request.CustomerUpdateRequest;

public class CustomerRequestToEntity {
    public static Customer toEntity(CustomerUpdateRequest request) {
        return new Customer(
                request.name(),
                request.age(),
                request.gender(),
                request.contact(),
                request.doctorName()
        );
    }
}
