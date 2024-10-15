package com.azu.hospital.ph.mediciens.icd_code.ndc.service;

import com.azu.hospital.ph.mediciens.icd_code.ndc.dao.ProductDao;
import com.azu.hospital.ph.mediciens.icd_code.ndc.entity.Product;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelUtility {
    private final ProductDao dao;


    @Autowired
    public ExcelUtility(@Qualifier("productDataAccessJpa") ProductDao dao) {
        this.dao = dao;

    }

    @Transactional
    public void loadProductsFromExcel() throws IOException {
        ClassPathResource resource = new ClassPathResource("icdCode/drugListNdc.xlsx");
        try (InputStream inputStream = resource.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip the header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            List<Product> products = new ArrayList<>();
            DataFormatter formatter = new DataFormatter();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Product product = new Product();

                // Use DataFormatter to handle potential type mismatches
                String productNDC = formatter.formatCellValue(row.getCell(0));
                String nonProprietaryName = formatter.formatCellValue(row.getCell(1));
                String dosageFormName = formatter.formatCellValue(row.getCell(2));
                String substanceName = formatter.formatCellValue(row.getCell(3));
                String activeNumeratorStrengthStr = formatter.formatCellValue(row.getCell(4));
                String activeIngredUnit = formatter.formatCellValue(row.getCell(5));

                // Attempt to parse the string into a BigDecimal, and handle any errors
                try {
                    BigDecimal activeNumeratorStrength = new BigDecimal(activeNumeratorStrengthStr);
                    product.setActiveNumeratorStrength(activeNumeratorStrength);
                } catch (NumberFormatException e) {
                    // Log the error and continue with the next row
                    System.err.println("Error parsing active numerator strength: " + e.getMessage());
                    continue;
                }

                product.setProductNDC(productNDC);
                product.setNonProprietaryName(nonProprietaryName);
                product.setDosageFormName(dosageFormName);
                product.setSubstanceName(substanceName);
                product.setActiveIngredUnit(activeIngredUnit);

                products.add(product);
            }

            dao.createProducts(products);
        }
    }


}

