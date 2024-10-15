package com.azu.hospital.ph.main_data_store.drugs_item.home_card.controller;


import com.azu.hospital.ph.main_data_store.drugs_item.home_card.service.HomeCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/drugs-item/home-card")
@RestController
@CrossOrigin
public class HomeCardController {
    private final HomeCardService homeCardService;

    public HomeCardController(HomeCardService homeCardService) {
        this.homeCardService = homeCardService;
    }

    @GetMapping
    public ResponseEntity<?> getHomeCard(){
        return ResponseEntity.ok(homeCardService.getHomeCardCounts());
    }
}
