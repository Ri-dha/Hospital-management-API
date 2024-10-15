package com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.controller;

import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.services.StoreHoseExpanseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/lab/store-hose/expanse")
public class StoreHoseExpanseController {

    private final StoreHoseExpanseService storeHoseExpanseService;

    @Autowired
    public StoreHoseExpanseController(StoreHoseExpanseService storeHoseExpanseService) {
        this.storeHoseExpanseService = storeHoseExpanseService;
    }
}
