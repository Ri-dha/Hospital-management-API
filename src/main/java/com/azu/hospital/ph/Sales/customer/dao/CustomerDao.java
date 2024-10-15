package com.azu.hospital.ph.Sales.customer.dao;


import com.azu.hospital.ph.Sales.customer.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomerDao {

    public void createCustomer(Customer customer);

    public void updateCustomer(Customer customer);

    public Optional<Customer> getCustomerById(Long id);

    public Page<Customer> getAllCustomer(Pageable pageable);

    public void deleteCustomerById(Long id);
}
