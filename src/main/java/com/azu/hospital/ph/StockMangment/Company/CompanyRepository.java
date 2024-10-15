package com.azu.hospital.ph.StockMangment.Company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    boolean existsCompanyByCompanyEmailContains(String companyEmail);

    Company findCompanyByCompanyNameIsContaining(String companyName);


}
