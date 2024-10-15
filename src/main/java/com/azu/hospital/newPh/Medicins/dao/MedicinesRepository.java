package com.azu.hospital.newPh.Medicins.dao;

import com.azu.hospital.newPh.Medicins.entity.Medicines;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicinesRepository extends JpaRepository<Medicines, Long> {


    @Query("SELECT m FROM Medicines m WHERE m.name =: name")
    List<Medicines> getByName(String name);


    @Query("SELECT m FROM Medicines m WHERE " +
            "(:name is null or m.name =: name) and " +
            "(:barCode is null or m.barCode =: barCode) and " +
            "(:price is null or m.price =: price) and " +
            "(:quantity is null or m.quantity =: quantity)")
    Page<Medicines> getAllByFilter(String name, String barCode, Long price, Long quantity, Pageable pageable);




}
