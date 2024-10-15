package com.azu.hospital.operation.surgical_list.dao;

import com.azu.hospital.operation.surgical_list.entity.MainSurgicalList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MainSurgicalListDao {

    void addSurgicalName(MainSurgicalList name);

    void updateSurgicalName( MainSurgicalList name);

    Optional<MainSurgicalList> getSurgicalById(Long id);

    Page<MainSurgicalList> getAllSurgicalList(String name, Pageable pageable);

    Boolean existsAllBySurgicalName(String name);

    List<MainSurgicalList> getAllSurgicalList();

    Page<MainSurgicalList> getAllWithPrice(Pageable pageable);

    Optional<MainSurgicalList> getSurgicalByName(String name);
}
