package com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.utils;


import com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.request.AnesthesiaPhysicianOrdersRequest;
import com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.entity.AnesthesiaPhysicianOrders;

public class AnesthesiaPhysicianOrdersRequestToChart {
  public static AnesthesiaPhysicianOrders requestToChart(AnesthesiaPhysicianOrdersRequest request){
    return new AnesthesiaPhysicianOrders.Builder()
            .withAnesthesiologistName(request.anesthesiologistName())
            .withOxygen(request.oxygen())
            .withIvPerHour(request.ivPerHour())
            .withIvRun(request.ivRun())
            .withDcIv(request.dcIv())
            .withToradol(request.toradol())
            .withToradolMg(request.toradolMg())
            .withOtherParenteral(request.otherParenteral())
            .withMotrin(request.motrin())
            .withOtherOral(request.otherOral())
            .withZofran4mg(request.zofran4mg())
            .withOtherZofran(request.otherZofran())
            .withAdditionalOrders(request.additionalOrders())
            .build();
  }
}
