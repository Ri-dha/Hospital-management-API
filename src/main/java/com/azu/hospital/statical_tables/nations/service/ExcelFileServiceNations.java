package com.azu.hospital.statical_tables.nations.service;


import com.azu.hospital.statical_tables.nations.dao.NationsDao;
import com.azu.hospital.statical_tables.nations.dto.NationsDto;
import com.azu.hospital.statical_tables.nations.dto.NationsDtoMapper;
import com.azu.hospital.statical_tables.nations.entity.Nations;
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

@Service("excelFileServiceNations")
public class ExcelFileServiceNations {
    private final NationsDao nationsFilesDao;

    private final NationsDtoMapper nationsDtoMapper;

    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public ExcelFileServiceNations(@Qualifier("NationsRepositoryJpa") NationsDao nationsFilesDao, @Qualifier("nationsDtoMapper") NationsDtoMapper nationsDtoMapper, ExceptionsMessageReturn messageReturn) {
        this.nationsFilesDao = nationsFilesDao;
        this.nationsDtoMapper = nationsDtoMapper;
        this.messageReturn = messageReturn;
    }


    @Transactional
    public void loadNationsFile() throws IOException {

        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        if (nationsFilesDao.getNationsCount() > 0) {
            throw new IllegalStateException(messages.getString("alreadyLoaded"));
        }

        ClassPathResource resource = new ClassPathResource("statiscalFiles/nations/nations.xlsx");

        try (InputStream inputStream = resource.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            List<Nations> nationsFiles = new ArrayList<>();
            DataFormatter formatter = new DataFormatter();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Nations nationsFile = new Nations();
                Long nationCode = Long.parseLong(formatter.formatCellValue(row.getCell(0)));
                String nationName = formatter.formatCellValue(row.getCell(1));
                nationsFile.setNationsCode(nationCode);
                nationsFile.setNationsTitle(nationName);
                nationsFiles.add(nationsFile);
            }

            nationsFilesDao.createNationsList(nationsFiles);


        }

    }


    public Page<NationsDto> searchNations(String nationsTitle, Long nationsCode, Pageable pageable) {
        return nationsFilesDao.searchNations(nationsTitle, nationsCode, pageable).map(nationsDtoMapper);
    }


}
