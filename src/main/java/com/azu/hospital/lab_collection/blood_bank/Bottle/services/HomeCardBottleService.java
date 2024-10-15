package com.azu.hospital.lab_collection.blood_bank.Bottle.services;

import com.azu.hospital.lab_collection.blood_bank.Bottle.dao.BottleDao;
import com.azu.hospital.lab_collection.blood_bank.Bottle.dto.HomeCardBottleDto;
import com.azu.hospital.ph.main_data_store.drugs_item.home_card.dto.HomeCardDto;
import com.azu.hospital.utils.enums.bottles.BottleStateEnum;
import com.azu.hospital.utils.enums.bottles.BottleTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeCardBottleService {

    private final BottleDao bottleDao;

    @Autowired
    public HomeCardBottleService(BottleDao bottleDao) {
        this.bottleDao = bottleDao;
    }



    public List<HomeCardBottleDto> getHomeCardBottle(){
        Long totalBlood = bottleDao.countAllByType(BottleTypeEnum.BLOOD);
        Long totalPlasma = bottleDao.countAllByType(BottleTypeEnum.PLASMA);
        Long totalPlatelet = bottleDao.countAllByType(BottleTypeEnum.PLATELETS);
        Long totalCRYO = bottleDao.countAllByType(BottleTypeEnum.CRYO);
        Long totalRBC = bottleDao.countAllByType(BottleTypeEnum.RBC);

        Long totalArchive = bottleDao.countAllArchived(BottleStateEnum.Archived);

        return List.of(
                new HomeCardBottleDto("Total Blood", totalBlood),
                new HomeCardBottleDto("Total Plasma", totalPlasma),
                new HomeCardBottleDto("Total CRYO", totalCRYO),
                new HomeCardBottleDto("Total Platelet", totalPlatelet),
                new HomeCardBottleDto("Total RBC", totalRBC),
                new HomeCardBottleDto("Total Archive", totalArchive)
        );


    }
}
