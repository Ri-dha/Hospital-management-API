package com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.dao;

import com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.entity.AnesthesiaPhysicianOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("AnesthesiaPhysicianOrdersJpaDataAccess")
public class AnesthesiaPhysicianOrdersJpaDataAccess implements AnesthesiaPhysicianOrdersDao {

  private final AnesthesiaPhysicianOrdersJpaRepository repository;

  @Autowired
  public AnesthesiaPhysicianOrdersJpaDataAccess(AnesthesiaPhysicianOrdersJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public AnesthesiaPhysicianOrders createNewChart(AnesthesiaPhysicianOrders chart) {
    return repository.save(chart);
  }

  @Override
  public AnesthesiaPhysicianOrders updateExistsChart(AnesthesiaPhysicianOrders update) {
    return repository.save(update);
  }

  @Override
  public Optional<AnesthesiaPhysicianOrders> findChartId(Long chartId) {
    return repository.findById(chartId);
  }

  @Override
  public List<AnesthesiaPhysicianOrders> getAllAnesthesiaPhysicianOrdersByPatientId(Long patientId) {
    return repository.getAllAnesthesiaPhysicianOrdersByPatientId(patientId);
  }
}
