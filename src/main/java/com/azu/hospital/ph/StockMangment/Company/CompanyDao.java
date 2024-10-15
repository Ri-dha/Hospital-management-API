package com.azu.hospital.ph.StockMangment.Company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CompanyDao {


    Page<Company> getAllCompany(Pageable pageable);

    Company addNewCompany(Company company);

    Company updateExistCompany(Company company);

    void deleteCompany(Integer companyId);

    Optional<Company> findCompanyById(Integer companyId);

    boolean existCompanyById(Integer companyId);

    boolean existCompanyByEmail(String companyEmail);

    Company findByName(String companyName);

//    void addNewCompany(String companyName, String companyEmail, String companyPhoneNumber, String companyAddress);
}
