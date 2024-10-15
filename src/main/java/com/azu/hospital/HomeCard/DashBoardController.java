package com.azu.hospital.HomeCard;


import com.azu.hospital.HomeCard.dto.DashBoardDto;
import com.azu.hospital.HomeCard.dto.DashBoardOperationDto;
import com.azu.hospital.utils.enums.operation.OperationStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/dashboard")
@CrossOrigin
public class DashBoardController {

    private final DashBoardService dashBoardService;

    @Autowired
    public DashBoardController(DashBoardService dashBoardService) {
        this.dashBoardService = dashBoardService;
    }


    @GetMapping("/get-dashboard")
    public DashBoardDto getDashBoard(){
        return dashBoardService.getDashBoard();
    }

    @GetMapping("/get-dashboard-operation")
    public DashBoardOperationDto getDashBoardOperation(
            @RequestParam String month){
        return dashBoardService.getDashBoardOperation(month);
    }

    @GetMapping("/get-dashboard-operation-analysis")
    public ResponseEntity<?> getDashBoardOperationAnalysis(){
        return ResponseEntity.ok(dashBoardService.getDashBoardOperationAnalysis());
    }
}
