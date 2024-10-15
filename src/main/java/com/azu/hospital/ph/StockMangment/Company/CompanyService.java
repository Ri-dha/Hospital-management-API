package com.azu.hospital.ph.StockMangment.Company;


import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.ph.mediciens.PageResponse;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.*;

@Service
public class CompanyService {

    private final CompanyDao companyDao;
    private final ExceptionsMessageReturn messageReturn;

    public CompanyService(@Qualifier("companyJpa") CompanyDao companyDao, ExceptionsMessageReturn messageReturn) {
        this.companyDao = companyDao;
        this.messageReturn = messageReturn;
    }

    public Map<String, Object> getAllCompanies(Pageable pageable) {
        Page<Company> companyPage = companyDao.getAllCompany(pageable);
       int page = 1;
       int size = 10;

        PageResponse<Company> response = new PageResponse<>(
                companyPage.getContent(),
                companyPage.getTotalElements(),
                companyPage.getNumber(),
                companyPage.getSize(),
                companyPage.getTotalPages(),
                companyPage.hasPrevious(),
                companyPage.hasNext()
        );

        if (companyPage.hasPrevious()) {
            int previousPageNumber = companyPage.getNumber() - 1;
            String previousPageUrl = "https://web.azu-app.com" + previousPageNumber + "&size=" + size;
            response.setPreviousPageUrl(previousPageUrl);
        }

        if (companyPage.hasNext()) {
            int nextPageNumber = companyPage.getNumber() + 1;
            String nextPageUrl = "https://web.azu-app.com" + nextPageNumber + "&size=" + size;
            response.setNextPageUrl(nextPageUrl);
        }

        Map<String, Object> responses = new HashMap<>();
        responses.put("data", companyPage.getContent());
        responses.put("message", "Companies retrieved successfully");
        responses.put("status", "true");
        responses.put("Pageable", response);
        return responses;
    }

    public Map<String, Object> addNewCompany(Company company) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        if (companyDao.existCompanyByEmail(company.getCompanyEmail())){
           throw new DuplicateResourceException(
                    messages.getString("existEmail") + company.getCompanyEmail()
           );
        }

        Company savedCompany = companyDao.addNewCompany(company);
        Map<String, Object> response = new HashMap<>();
        response.put("data", savedCompany);
        response.put("message", "Company added successfully");
        response.put("status", "true");
        return response;
    }

    public Map<String, Object> updateExistingCompany(Company company) {
        if (companyDao.existCompanyById(company.getCompanyId())) {
            Company updatedCompany = companyDao.updateExistCompany(company);
            Map<String, Object> response = new HashMap<>();
            response.put("data", updatedCompany);
            response.put("message", "Company updated successfully");
            response.put("status", "true");
            return response;
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Company not found");
            response.put("status", "false");
            return response;
        }
    }

    public Map<String, Object> deleteCompany(Integer companyId) {
        if (companyDao.existCompanyById(companyId)) {
            companyDao.deleteCompany(companyId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Company deleted successfully");
            response.put("status", "true");
            return response;
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Company not found");
            response.put("status", "false");
            return response;
        }
    }

    public Map<String, Object> findCompanyById(Integer companyId) {
        Optional<Company> company = companyDao.findCompanyById(companyId);
        Map<String, Object> response = new HashMap<>();
        if (company != null) {
            response.put("data", company);
            response.put("message", "Company found");
            response.put("status", "success");
        } else {
            response.put("message", "Company not found");
            response.put("status", "error");
        }
        return response;
    }

    public boolean existCompanyByEmail(String companyEmail) {
        return companyDao.existCompanyByEmail(companyEmail);
    }
}
