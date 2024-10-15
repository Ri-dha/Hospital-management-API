package com.azu.hospital.ph.Sales.customer.dao;

import com.azu.hospital.ph.Sales.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
