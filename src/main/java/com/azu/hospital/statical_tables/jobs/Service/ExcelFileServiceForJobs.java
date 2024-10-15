package com.azu.hospital.statical_tables.jobs.Service;


import com.azu.hospital.statical_tables.jobs.dao.JobFilesDao;
import com.azu.hospital.statical_tables.jobs.dto.JobsDto;
import com.azu.hospital.statical_tables.jobs.dto.JobsDtoMapper;
import com.azu.hospital.statical_tables.jobs.entity.JobsFile;
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

@Service("excelFileServiceForJobs")
public class ExcelFileServiceForJobs {

    private final JobFilesDao jobFilesDao;

    private final JobsDtoMapper jobsDtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public ExcelFileServiceForJobs(@Qualifier("JobsJpa") JobFilesDao jobFilesDao, @Qualifier("jobsDtoMapper") JobsDtoMapper jobsDtoMapper, ExceptionsMessageReturn messageReturn) {
        this.jobFilesDao = jobFilesDao;
        this.jobsDtoMapper = jobsDtoMapper;
        this.messageReturn = messageReturn;
    }

    @Transactional
    public void loadJobsFile() throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        if (jobFilesDao.getJobsCount() > 0) {
            throw new IllegalStateException(messages.getString("alreadyLoaded"));
        }


        ClassPathResource resource = new ClassPathResource("statiscalFiles/jobs/job1.xlsx");

        try (InputStream inputStream = resource.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            List<JobsFile> jobsFiles = new ArrayList<>();
            DataFormatter formatter = new DataFormatter();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                JobsFile jobsFile = new JobsFile();
                Long jobCode = Long.parseLong(formatter.formatCellValue(row.getCell(0)));
                String jobTitle = formatter.formatCellValue(row.getCell(1));

                jobsFile.setJobCode(jobCode);
                jobsFile.setJobTitle(jobTitle);
                jobsFiles.add(jobsFile);
            }
            jobFilesDao.createJobFilesList(jobsFiles);


        }

    }


    public Page<JobsDto> searchJobFiles(String jobTitle, Long jobCode, Pageable pageable) {
        return jobFilesDao.searchJobFiles(jobTitle, jobCode, pageable).map(jobsDtoMapper);
    }


}
