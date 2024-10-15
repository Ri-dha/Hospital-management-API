package com.azu.hospital.lab_collection.blood_bank.Bottle.canceledBottle.dao;

import com.azu.hospital.lab_collection.blood_bank.Bottle.canceledBottle.entity.CanceledBottles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CanceledBottleRepository extends JpaRepository<CanceledBottles ,Long> {
}
