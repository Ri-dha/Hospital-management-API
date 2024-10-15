package com.azu.hospital.statical_tables.district;


import com.azu.hospital.statical_tables.district.dao.DistrictDao;
import com.azu.hospital.statical_tables.district.dto.DistrictDto;
import com.azu.hospital.statical_tables.district.dto.DistrictDtoMapper;
import com.azu.hospital.statical_tables.district.entity.District;
import com.azu.hospital.statical_tables.provinces.dao.ProvincesDao;
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

@Service
public class DistrictService {

    private final DistrictDao districtDao;
    private final DistrictDtoMapper districtMapper;
    private final ExceptionsMessageReturn messageReturn;

    private final ProvincesDao provincesDao;

    @Autowired
    public DistrictService(@Qualifier("districtrepo") DistrictDao districtDao,
                           @Qualifier("districtDtoMapper") DistrictDtoMapper districtMapper,
                           @Qualifier("exceptionsMessageReturn") ExceptionsMessageReturn messageReturn,
                           @Qualifier("ProvincesRepository") ProvincesDao provincesDao) {
        this.districtDao = districtDao;
        this.districtMapper = districtMapper;
        this.messageReturn = messageReturn;
        this.provincesDao = provincesDao;
    }


    Page<DistrictDto> getDistrictList(Pageable pageable) {
        return districtDao.getDistrict(pageable).map(districtMapper);
    }

    Page<DistrictDto> searchDistrict(String name, Long districtCode, Pageable pageable) {
        return districtDao.searchDistrict(name, districtCode, pageable).map(districtMapper);
    }


    @Transactional
    public void loadDistrictFile() throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        if (districtDao.getDistrictCount() > 0) {
            throw new IllegalStateException(
                    messages.getString("alreadyLoaded")
            );
        }

        ClassPathResource resource = new ClassPathResource("statiscalFiles/district/districts.xlsx");

        try (InputStream inputStream = resource.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            List<District> districtList = new ArrayList<>();
            DataFormatter formatter = new DataFormatter();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                District district = new District();
                Long provinceCode = Long.parseLong(formatter.formatCellValue(row.getCell(0)));
                String districtName = formatter.formatCellValue(row.getCell(1));
                Provinces provinces = provincesDao.getProvinceByProvinceCode(provinceCode);
                district.setProvinces(provinces);
                district.setDistrictName(districtName);
                districtList.add(district);
            }
            districtDao.createDistrictList(districtList);

        }


    }


}
