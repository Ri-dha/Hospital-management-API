package com.azu.hospital.ph.Sales.customer.dao;

import com.azu.hospital.ph.Sales.customer.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("customer_dao_repository")
public class CustomerJpaDataAccess implements CustomerDao {

    private final CustomerRepository repository;

    @Autowired
    public CustomerJpaDataAccess(CustomerRepository repository) {
        this.repository = repository;
    }


    @Override
    public void createCustomer(Customer customer) {
        repository.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        repository.save(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<Customer> getAllCustomer(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void deleteCustomerById(Long id) {
        repository.deleteById(id);
    }
}
