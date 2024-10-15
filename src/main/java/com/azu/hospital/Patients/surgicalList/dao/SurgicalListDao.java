package com.azu.hospital.Patients.surgicalList.dao;
import com.azu.hospital.Patients.surgicalList.entity.SurgicalList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface SurgicalListDao {

    Optional<SurgicalList> findById(Long id);

    SurgicalList createSurgicalList(SurgicalList surgicalList);

    Page<SurgicalList> getAllSurgicalList(Instant surgicalDate , Pageable pageable);

    void updateSurgicalList(SurgicalList surgicalList);

}
