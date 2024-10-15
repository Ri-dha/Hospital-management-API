package com.azu.hospital.operation.surgical_list.dao;

import com.azu.hospital.operation.surgical_list.entity.MainSurgicalList;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Transactional
public interface MainSurgicalListRepository extends JpaRepository<MainSurgicalList, Long> {


    @Query("SELECT l FROM MainSurgicalList l WHERE :name IS NULL OR LOWER(l.surgicalName) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<MainSurgicalList> findAllBySurgicalName(@Param("name") String name, Pageable pageable);


    @Query("SELECT COUNT(l) > 0 FROM MainSurgicalList l WHERE :name IS NULL OR LOWER(l.surgicalName) LIKE LOWER(CONCAT('%', :name, '%'))")
    Boolean existsAllBySurgicalName(@Param("name") String name);

    @Query("SELECT l FROM MainSurgicalList l WHERE l.price > 0")
    Page<MainSurgicalList> getAllWithPrice(Pageable pageable);

    @Query("SELECT l FROM MainSurgicalList l WHERE l.surgicalName = :name")
    Optional<MainSurgicalList> getSurgicalByName(@Param("name") String name);
}
