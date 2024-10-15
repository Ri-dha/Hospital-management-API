package com.azu.hospital.lab_collection.blood_bank.Bottle.services;

import com.azu.hospital.lab_collection.blood_bank.Bottle.dao.BottleDao;
import com.azu.hospital.lab_collection.blood_bank.Bottle.dto.BottleDto;
import com.azu.hospital.lab_collection.blood_bank.Bottle.dto.BottleDtoMapper;
import com.azu.hospital.lab_collection.blood_bank.Bottle.entity.Bottle;
import com.azu.hospital.lab_collection.blood_bank.Bottle.request.CreateNewBottleRequest;
import com.azu.hospital.lab_collection.blood_bank.BottleOrder.dao.BottleOrderDao;
import com.azu.hospital.utils.enums.bottles.BloodGroupEnum;
import com.azu.hospital.utils.enums.bottles.BottleStateEnum;
import com.azu.hospital.utils.enums.bottles.BottleTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BottleService {

    private final BottleDao bottleDao;
    private final BottleOrderDao order;
    private final BottleDtoMapper mapper;

    @Autowired
    public BottleService(
            @Qualifier("bottleRepo") BottleDao bottleDao, BottleOrderDao order,
            BottleDtoMapper mapper
    ) {
        this.bottleDao = bottleDao;
        this.order = order;
        this.mapper = mapper;
    }


    public Page<BottleDto> getNotArchiveBottle(
            BottleTypeEnum type,
            String search,
            Pageable pageable
    ) {
        return bottleDao.
                getAllAndNoArchivedAndBottleType(
                        type,
                        search,
                        search,
                        pageable
                ).
                map(mapper::toDto);
    }

    public void createNewBottle(CreateNewBottleRequest request) {

        Bottle bottle = new Bottle(
                request.getBottleType(),
                request.getBloodGroup(),
                request.getBottleNumber(),
                request.getDonatorName(),
                request.getDonatorPhoneNumber(),
                request.getFillDate(),
                request.getExpiredDate(),
                BottleStateEnum.NoArchive
        );

        bottleDao.createNewBottle(bottle);
    }

    public Map<String, Object> getCountOfBottle(BottleTypeEnum bottleType) {

        List<BloodGroupEnum> bloodGroupEnum = List.of(BloodGroupEnum.values());

        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> counts = new ArrayList<>();

        int total = 0;

        for (BloodGroupEnum blood : bloodGroupEnum) {
            int count = bottleDao.countAllByStateAndBottleType(blood, bottleType) -
                    (order.sumCountOfBottleInOrder(blood, bottleType) == null ?
                            0 : order.sumCountOfBottleInOrder(blood, bottleType));

            total += count;

            Map<String, Object> entry = new HashMap<>();
            entry.put("name", blood.toString());
            entry.put("count", count);

            counts.add(entry);
        }

        result.put("total", total);
        result.put("counts", counts);

        return result;
    }



    public Page<BottleDto> getBottleByStateAndBottleType(
            List<BottleStateEnum> states,
            BottleTypeEnum bottleType,
            Pageable pageable
    ) {
        return bottleDao.getAllByListOfStatesAndBottleType(
                states,
                bottleType,
                pageable
        ).map(mapper::toDto);
    }

    public Page<BottleDto> getBottleArchive(Pageable pageable) {
        Page<Bottle> bottles = bottleDao.getAllBottlesArchive(
                BottleStateEnum.Archived,
                pageable
        );

        return bottles.map(mapper::toDto);
    }


}
