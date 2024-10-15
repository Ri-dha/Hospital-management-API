package com.azu.hospital.sitting;

import com.azu.hospital.sitting.assets.AssetCleanupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin

public class AdminController {


    private final AssetCleanupService assetCleanupService;

    @Autowired
    public AdminController(AssetCleanupService assetCleanupService) {
        this.assetCleanupService = assetCleanupService;
    }

    @PostMapping("/cleanup-assets")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> cleanupAssets() {
        return ResponseEntity.ok(assetCleanupService.cleanupAssets());
    }
}
