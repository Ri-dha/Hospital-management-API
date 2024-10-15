//package com.azu.hospital.ph.drug_list;
//
//import org.apache.poi.ss.usermodel.*;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//public class MainDrugTradeNameList {
//
//    private final ResourceLoader resourceLoader;
//
//    public MainDrugTradeNameList(ResourceLoader resourceLoader) {
//        this.resourceLoader = resourceLoader;
//    }
//
//    @GetMapping("/parse-excel")
//    public List<String> parseExcel() {
//        List<String> parsedData = new ArrayList<>();
//
//        try {
//            ClassPathResource resource = new ClassPathResource("icdCode/druglistnames.xlsx");
//            InputStream inputStream = resource.getInputStream();
//            Workbook workbook = WorkbookFactory.create(inputStream);
//            Sheet datatypeSheet = workbook.getSheetAt(0);
//
//            for (Row currentRow : datatypeSheet) {
//                for (Cell currentCell : currentRow) {
//                    String cellValue = currentCell.getStringCellValue();
//                    String[] words = cellValue.split("\\s+");
//
//                    // Concatenate only the first three words or less
//                    StringBuilder parsedCell = new StringBuilder();
//                    for (int i = 0; i < Math.min(2, words.length); i++) {
//                        parsedCell.append(words[i]).append(" ");
//                    }
//                    parsedData.add(parsedCell.toString().trim());
//                }
//            }
//            workbook.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return parsedData;
//    }
//}
