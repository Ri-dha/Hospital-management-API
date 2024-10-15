package com.azu.hospital.statical_tables.district;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/district")
@CrossOrigin
public class DistrictController {

    private final DistrictService districtService;

    @Autowired
    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @PostMapping("/load-data")
    public ResponseEntity<String> loadData(){
        try {
            districtService.loadDistrictFile();
            return ResponseEntity.ok("Data loaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error while loading data" + e.getMessage());
        }
    }


    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "districtCode", required = false) Long districtCode,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(districtService.searchDistrict(name, districtCode,pageable));
    }




}
