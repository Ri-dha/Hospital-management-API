package com.azu.hospital.ph.StockMangment.Company;

import org.springframework.stereotype.Service;

@Service
public class CompanyDtoMapper {
    public CompanyDto toDto(Company company) {
        if (company == null) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();
        companyDto.setCompanyId(company.getCompanyId());
        companyDto.setCompanyName(company.getCompanyName());
        companyDto.setCompanyEmail(company.getCompanyEmail());
        companyDto.setCompanyPhoneNumber(company.getCompanyPhoneNumber());
        companyDto.setCompanyAddress(company.getCompanyAddress());

        return companyDto;
    }

    public Company toEntity(CompanyDto companyDto) {
        if (companyDto == null) {
            return null;
        }

        Company company = new Company();
        company.setCompanyId(companyDto.getCompanyId());
        company.setCompanyName(companyDto.getCompanyName());
        company.setCompanyEmail(companyDto.getCompanyEmail());
        company.setCompanyPhoneNumber(companyDto.getCompanyPhoneNumber());
        company.setCompanyAddress(companyDto.getCompanyAddress());

        return company;
    }
}

