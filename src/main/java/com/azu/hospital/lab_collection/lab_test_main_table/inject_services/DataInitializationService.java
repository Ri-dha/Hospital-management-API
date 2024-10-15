package com.azu.hospital.lab_collection.lab_test_main_table.inject_services;

import com.azu.hospital.lab_collection.lab_test_main_table.dao.LabTestMainTableDao;
import com.azu.hospital.lab_collection.lab_test_main_table.entity.LabTestMainTableForMainTestName;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

@Service
public class DataInitializationService  {

    private final LabTestMainTableDao labTestRepository;


    @Autowired
    public DataInitializationService(LabTestMainTableDao labTestRepository) {
        this.labTestRepository = labTestRepository;
    }




    public void loadLabData() throws Exception {
            ClassPathResource resource = new ClassPathResource("lablist/labtest.xlsx");
        try (InputStream in = resource.getInputStream(); Workbook workbook = new XSSFWorkbook(in)) {
            Sheet sheet = workbook.getSheetAt(0);
            Set<String> processedNames = new HashSet<>();

            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null) {
                    String labTestName = cell.getStringCellValue().trim();
                    if (!processedNames.contains(labTestName) && !labTestRepository.existsAllByLabTestNameName(labTestName)) {
                        LabTestMainTableForMainTestName labTestMainTableForMainTestName = new LabTestMainTableForMainTestName(labTestName);
                        labTestRepository.addNewTestToMainTable(labTestMainTableForMainTestName);
                        processedNames.add(labTestName);
                    }
                }
            }
        }
    }

}
