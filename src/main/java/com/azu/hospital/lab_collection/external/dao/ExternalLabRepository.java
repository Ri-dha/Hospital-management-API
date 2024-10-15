package com.azu.hospital.lab_collection.external.dao;

import com.azu.hospital.lab_collection.external.dto.ExternalLabDto;
import com.azu.hospital.lab_collection.external.entity.ExternalLabTest;
import com.azu.hospital.lab_collection.internal.entity.InternalLabTest;
import com.azu.hospital.lab_collection.request.DateTimeTest;
import com.azu.hospital.utils.enums.lab.LabRequestStatusEnum;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
public interface ExternalLabRepository extends JpaRepository<ExternalLabTest, Long> {


    @Query("SELECT e FROM ExternalLabTest e WHERE e.state IN :state")
    Page<ExternalLabTest> getExternalLabTest(@Param("state") List<LabRequestStatusEnum> states, Pageable pageable);

    Page<ExternalLabTest> getAllByStateIsNotOrderByAdmissionDateDesc(LabRequestStatusEnum state, Pageable pageable);
    @Query("SELECT e FROM ExternalLabTest e WHERE e.state IN :state ORDER BY e.admissionDate DESC")
    Page<ExternalLabTest> getAllByStateIsOrderByAdmissionDateDesc(@Param("state")List<LabRequestStatusEnum> state, Pageable pageable);





    @Query(value = "SELECT COUNT(*) AS count, CAST(EXTRACT(HOUR FROM i.created_at) AS INTEGER) AS hour FROM " +
            "external_lab_test" +
            " " +
            "i WHERE DATE(i.created_at) = :date GROUP BY CAST(EXTRACT(HOUR FROM i.created_at) AS INTEGER) order by " +
            "hour",
            nativeQuery =
                    true)
    List<DateTimeTest> getDateTimeTestRaw(LocalDate date);


    @Query("SELECT e FROM ExternalLabTest e JOIN e.testRequests r JOIN r.extTestRequest ir WHERE e.id = :labId")
    Optional<ExternalLabTest> findByRequestId(@Param("labId") Long requestId);

}
