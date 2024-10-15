package com.azu.hospital.ph.StockMangment.Company;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("companyJpa")
@Qualifier("companyJpa")
public class CompanyJPADataAccess implements CompanyDao{
    private final CompanyRepository companyRepository;

    public CompanyJPADataAccess(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @Override
    public Page<Company> getAllCompany(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    @Override
    public Company addNewCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company updateExistCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Integer companyId) {
        companyRepository.deleteById(companyId);
    }

    @Override
    public Optional<Company> findCompanyById(Integer companyId) {
        return companyRepository.findById(companyId);

    }

    @Override
    public boolean existCompanyById(Integer companyId) {
        return companyRepository.existsById(companyId);
    }

    @Override
    public boolean existCompanyByEmail(String companyEmail) {
        return companyRepository.existsCompanyByCompanyEmailContains(companyEmail);
    }

    @Override
    public Company findByName(String companyName) {
        return companyRepository.findCompanyByCompanyNameIsContaining(companyName);
    }

//    @Override
//    public void addNewCompany(String companyName, String companyEmail, String companyPhoneNumber, String companyAddress) {
//        companyRepository.saveAll(companyName, companyEmail, companyPhoneNumber, companyAddress);
//    }

}
