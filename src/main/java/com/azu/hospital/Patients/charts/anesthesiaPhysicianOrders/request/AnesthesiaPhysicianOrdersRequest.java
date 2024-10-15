package com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.request;


import com.azu.hospital.Patients.charts.physical_assessment_enm.OxygenOrder;

public record AnesthesiaPhysicianOrdersRequest(
        String anesthesiologistName,
        OxygenOrder oxygen,
        Integer ivPerHour,
        Boolean ivRun,
        Boolean dcIv,
        Boolean toradol,
        Integer toradolMg,
        Boolean otherParenteral,
        Boolean motrin,
        String otherOral,
        Boolean zofran4mg,
        Boolean otherZofran,
        String additionalOrders
) {
}
