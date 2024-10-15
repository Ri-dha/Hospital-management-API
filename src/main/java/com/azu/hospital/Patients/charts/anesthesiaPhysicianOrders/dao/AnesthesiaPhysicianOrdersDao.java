package com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.dao;

import com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.entity.AnesthesiaPhysicianOrders;

import java.util.List;
import java.util.Optional;

public interface AnesthesiaPhysicianOrdersDao {

  AnesthesiaPhysicianOrders createNewChart(AnesthesiaPhysicianOrders chart);

  AnesthesiaPhysicianOrders updateExistsChart(AnesthesiaPhysicianOrders update);

  Optional<AnesthesiaPhysicianOrders> findChartId(Long chartId);

  List<AnesthesiaPhysicianOrders> getAllAnesthesiaPhysicianOrdersByPatientId(Long patientId);

}
