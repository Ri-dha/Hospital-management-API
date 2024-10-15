package com.azu.hospital.ph.main_data_store.drugs_item.inject;

import com.azu.hospital.ph.main_data_store.drugs_item.dao.DrugItemDao;
import com.azu.hospital.ph.main_data_store.drugs_item.entity.DrugsItem;
import com.azu.hospital.ph.mediciens.type.MedicineType;
import io.micrometer.core.instrument.MultiGauge;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

@Service
public class DrugsExcelService {

    private final DrugItemDao drugsItemRepository;

    @Autowired
    public DrugsExcelService(DrugItemDao drugsItemRepository) {
        this.drugsItemRepository = drugsItemRepository;
    }

    public void importDataFromExcel(String filePath) {
        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            // Skip header row if exists
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                DrugsItem drugsItem = createDrugsItemFromRow(row);
                drugsItemRepository.insertDrug(drugsItem);
            }

            workbook.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DrugsItem createDrugsItemFromRow(Row row) {
        DrugsItem drugsItem = new DrugsItem();
        drugsItem.setItemName(getCellValue(row.getCell(0)));
        drugsItem.setType(getCellValue(row.getCell(1)));
        drugsItem.setDose(getCellValue(row.getCell(2)));
        drugsItem.setItemCompany(getCellValue(row.getCell(4)));
        drugsItem.setDescription(getCellValue(row.getCell(3)));

        return drugsItem;
    }

    private String getCellValue(Cell cell) {
        return cell == null ? null : cell.toString();
    }

}
