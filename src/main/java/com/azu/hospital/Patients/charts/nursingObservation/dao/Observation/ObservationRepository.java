package com.azu.hospital.Patients.charts.nursingObservation.dao.Observation;

import com.azu.hospital.Patients.charts.nursingObservation.entity.ObservationTime;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Transactional
public interface ObservationRepository extends JpaRepository<ObservationTime,Long> {

    @Query("SELECT o FROM ObservationTime o WHERE o.nursingObservation.id=:chartId")
    List<ObservationTime> findAllByChartId(Long chartId);
}
