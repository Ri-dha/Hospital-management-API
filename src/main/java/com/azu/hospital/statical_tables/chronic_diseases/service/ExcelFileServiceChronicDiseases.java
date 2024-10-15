package com.azu.hospital.statical_tables.chronic_diseases.service;


import com.azu.hospital.statical_tables.chronic_diseases.dao.ChronicDiseasesDao;
import com.azu.hospital.statical_tables.chronic_diseases.dto.ChronicDiseasesDto;
import com.azu.hospital.statical_tables.chronic_diseases.dto.ChronicDiseasesDtoMapper;
import com.azu.hospital.statical_tables.chronic_diseases.entity.ChronicDiseases;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service("excelFileServiceChronicDiseases")
public class ExcelFileServiceChronicDiseases {
    private final ChronicDiseasesDtoMapper chronicDiseasesDtoMapper;

    private final ChronicDiseasesDao chronicDiseasesDao;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public ExcelFileServiceChronicDiseases(@Qualifier("chronicDiseasesDtoMapper") ChronicDiseasesDtoMapper chronicDiseasesDtoMapper,
                                           @Qualifier("ChronicDiseasesDao") ChronicDiseasesDao chronicDiseasesDao, ExceptionsMessageReturn messageReturn) {
        this.chronicDiseasesDtoMapper = chronicDiseasesDtoMapper;
        this.chronicDiseasesDao = chronicDiseasesDao;
        this.messageReturn = messageReturn;
    }


    @Transactional
    public void loadChronicDiseasesFile() throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        if (chronicDiseasesDao.getChronicDiseasesCount() > 0) {
            throw new IllegalStateException(messages.getString("alreadyLoaded"));
        }

        ClassPathResource resource = new ClassPathResource("statiscalFiles/chronicDiseases/chronic.xlsx");

        try (InputStream inputStream = resource.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            List<ChronicDiseases> chronicDiseasesList = new ArrayList<>();
            DataFormatter formatter = new DataFormatter();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                ChronicDiseases chronicDiseases = new ChronicDiseases();
                Long diseaseCode = Long.parseLong(formatter.formatCellValue(row.getCell(0)));
                String diseaseName = formatter.formatCellValue(row.getCell(1));
                chronicDiseases.setDiseaseCode(diseaseCode);
                chronicDiseases.setDiseaseName(diseaseName);
                chronicDiseasesList.add(chronicDiseases);
            }
            chronicDiseasesDao.createChronicDiseasesList(chronicDiseasesList);

        }
    }


    public Page<ChronicDiseasesDto> searchChronicDiseases(String name, Long diseaseCode, Pageable pageable) {
        return chronicDiseasesDao.searchChronicDiseases(name, diseaseCode, pageable).map(chronicDiseasesDtoMapper);
    }

}
