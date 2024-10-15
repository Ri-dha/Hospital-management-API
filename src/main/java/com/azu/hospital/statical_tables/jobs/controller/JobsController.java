package com.azu.hospital.statical_tables.jobs.controller;

import com.azu.hospital.statical_tables.jobs.Service.ExcelFileServiceForJobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/jobs")
@CrossOrigin
public class JobsController {
    private final ExcelFileServiceForJobs excelFileServiceForJobs;


    @Autowired
    public JobsController(@Qualifier("excelFileServiceForJobs") ExcelFileServiceForJobs excelFileServiceForJobs) {
        this.excelFileServiceForJobs = excelFileServiceForJobs;
    }

    @PostMapping("/load-data")
    public ResponseEntity<String> loadData() {
        try {
            excelFileServiceForJobs.loadJobsFile();
            return ResponseEntity.ok("Data loaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while loading data" + e.getMessage());
        }
    }


    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam(value = "jobTitle", required = false) String jobTitle,
            @RequestParam(value = "jobCode", required = false) Long jobCode,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(excelFileServiceForJobs.searchJobFiles(jobTitle, jobCode, pageable));
    }

}
