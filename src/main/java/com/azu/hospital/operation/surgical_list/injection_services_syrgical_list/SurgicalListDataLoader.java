//package com.azu.hospital.operation.surgical_list.injection_services_syrgical_list;
//
//import com.azu.hospital.operation.surgical_list.dao.MainSurgicalListDao;
//import com.azu.hospital.operation.surgical_list.entity.MainSurgicalList;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Component;
//
//import java.io.InputStream;
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//public class SurgicalListDataLoader implements ApplicationRunner {
//
//    private final MainSurgicalListDao mainSurgicalListDao;
//
//
//    @Autowired
//    public SurgicalListDataLoader(MainSurgicalListDao mainSurgicalListDao) {
//        this.mainSurgicalListDao = mainSurgicalListDao;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        ClassPathResource resource = new ClassPathResource("oprations/surgicalList.xlsx");
//        try (InputStream in = resource.getInputStream(); Workbook workbook = new XSSFWorkbook(in)) {
//            Sheet sheet = workbook.getSheetAt(0);
//            Set<String> processedNames = new HashSet<>();
//
//            for (Row row : sheet) {
//                Cell cell = row.getCell(0);
//                if (cell != null) {
//                    String surgicalName = cell.getStringCellValue().trim();
//                    if (!processedNames.contains(surgicalName) && !mainSurgicalListDao.existsAllBySurgicalName(surgicalName)) {
//                        MainSurgicalList surgicalList = new MainSurgicalList(surgicalName);
//                        mainSurgicalListDao.addSurgicalName(surgicalList);
//                        processedNames.add(surgicalName);
//                    }
//                }
//            }
//        }
//    }
//}
