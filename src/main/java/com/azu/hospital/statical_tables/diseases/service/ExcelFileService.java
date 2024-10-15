package com.azu.hospital.statical_tables.diseases.service;

import com.azu.hospital.statical_tables.diseases.dao.DiseasesDao;
import com.azu.hospital.statical_tables.diseases.dto.DiseasesDtoMapper;
import com.azu.hospital.statical_tables.diseases.entity.Diseases;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Service
public class ExcelFileService {
    private final DiseasesDao diseasesDao;
    private final DiseasesDtoMapper diseasesDtoMapper;

    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public ExcelFileService(
            @Qualifier("diseasesRepositoryJpa") DiseasesDao diseasesDao,
            @Qualifier("diseasesDtoMapper") DiseasesDtoMapper diseasesDtoMapper, ExceptionsMessageReturn messageReturn) {
        this.diseasesDao = diseasesDao;
        this.diseasesDtoMapper = diseasesDtoMapper;
        this.messageReturn = messageReturn;
    }


    @Transactional
    public void loadDiseasesFromExcel() throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        if (diseasesDao.getDiseasesCount() > 0) {
            throw new IllegalStateException(messages.getString("alreadyLoaded"));
        }

        ClassPathResource resource = new ClassPathResource("statiscalFiles/diseases/diseasesList.xlsx");
        try (InputStream inputStream = resource.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip the header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            List<Diseases> diseases = new ArrayList<>();
            DataFormatter formatter = new DataFormatter();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Diseases disease = new Diseases();

                String code = formatter.formatCellValue(row.getCell(0));
                String name = formatter.formatCellValue(row.getCell(1));
                String serialNumber = formatter.formatCellValue(row.getCell(2));

                disease.setCode(code);
                disease.setName(name);
                disease.setSerialNumber(serialNumber);
                diseases.add(disease);

            }
            diseasesDao.createDiseasesList(diseases);
        }


    }


}