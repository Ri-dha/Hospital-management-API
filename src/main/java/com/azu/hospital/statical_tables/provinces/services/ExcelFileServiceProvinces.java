package com.azu.hospital.statical_tables.provinces.services;


import com.azu.hospital.statical_tables.provinces.dao.ProvincesDao;
import com.azu.hospital.statical_tables.provinces.dto.ProvincesDto;
import com.azu.hospital.statical_tables.provinces.dto.ProvincesDtoMapper;
import com.azu.hospital.statical_tables.provinces.entity.Provinces;
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

@Service("excelFileServiceProvinces")
public class ExcelFileServiceProvinces {
    private final ProvincesDtoMapper provincesDtoMapper;
    private final ProvincesDao provincesDao;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public ExcelFileServiceProvinces(@Qualifier("provincesDtoMapper") ProvincesDtoMapper provincesDtoMapper,
                                     @Qualifier("ProvincesRepository") ProvincesDao provincesDao, ExceptionsMessageReturn messageReturn) {
        this.provincesDtoMapper = provincesDtoMapper;
        this.provincesDao = provincesDao;
        this.messageReturn = messageReturn;
    }

    @Transactional
    public void loadProvincesFile()throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());


        if(provincesDao.getProvincesCount() > 0){
         throw new IllegalStateException(
                    messages.getString("alreadyLoaded")
         );
        }

        ClassPathResource resource = new ClassPathResource("statiscalFiles/provinces/provinces.xlsx");

        try(InputStream inputStream = resource.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            List<Provinces> provincesList = new ArrayList<>();
            DataFormatter formatter = new DataFormatter();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Provinces provinces = new Provinces();
                Long provinceCode = Long.parseLong(formatter.formatCellValue(row.getCell(0)));
                String provinceName = formatter.formatCellValue(row.getCell(1));
                provinces.setProvinceCode(provinceCode);
                provinces.setProvinceName(provinceName);
                provincesList.add(provinces);
            }
            provincesDao.createProvincesList(provincesList);
        }



    }



    public Page<ProvincesDto> searchProvinces(String name, Long provinceCode, Pageable pageable) {
        return provincesDao.searchProvinces(name, provinceCode, pageable).map(provincesDtoMapper);
    }






}
