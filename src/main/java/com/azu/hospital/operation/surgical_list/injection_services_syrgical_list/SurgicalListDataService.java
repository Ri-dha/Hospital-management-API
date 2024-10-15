package com.azu.hospital.operation.surgical_list.injection_services_syrgical_list;

import com.azu.hospital.operation.surgical_list.entity.MainSurgicalList;
import com.azu.hospital.operation.surgical_list.dao.MainSurgicalListDao;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

@Service
public class SurgicalListDataService {

    private final MainSurgicalListDao mainSurgicalListDao;

    @Autowired
    public SurgicalListDataService(MainSurgicalListDao mainSurgicalListDao) {
        this.mainSurgicalListDao = mainSurgicalListDao;
    }

    public void loadSurgicalData() throws Exception {
        ClassPathResource resource = new ClassPathResource("oprations/surgicalList.xlsx");
        try (InputStream in = resource.getInputStream(); Workbook workbook = new XSSFWorkbook(in)) {
            Sheet sheet = workbook.getSheetAt(0);
            Set<String> processedNames = new HashSet<>();
            DataFormatter dataFormatter = new DataFormatter();
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null) {
                    String surgicalName = cell.getStringCellValue().trim();
                    String surgicalCode = dataFormatter.formatCellValue(row.getCell(1));
                    if (!processedNames.contains(surgicalName) && !mainSurgicalListDao.existsAllBySurgicalName(surgicalName)) {
                        MainSurgicalList surgicalList = new MainSurgicalList(surgicalName);
                        surgicalList.setSurgicalCode(surgicalCode);
                        mainSurgicalListDao.addSurgicalName(surgicalList);
                        processedNames.add(surgicalName);
                    }
                }
            }
        }
    }
}
