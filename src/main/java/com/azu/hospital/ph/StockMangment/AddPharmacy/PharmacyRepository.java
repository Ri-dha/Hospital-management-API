package com.azu.hospital.ph.StockMangment.AddPharmacy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {
    @Query("SELECT p FROM Pharmacy p WHERE p.pharmacyName = :name")
    Pharmacy getPharmacyByName(@Param("name") String pharmacyName);
}
