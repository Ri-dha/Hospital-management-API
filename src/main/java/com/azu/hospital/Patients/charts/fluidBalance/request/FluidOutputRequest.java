package com.azu.hospital.Patients.charts.fluidBalance.request;

import com.azu.hospital.Patients.charts.fluidBalance.fluid_balance_classes.FieldFluidRequest;

public record FluidOutputRequest(
        String time,
        FieldFluidRequest urine,
        FieldFluidRequest bowelOrStoma,
        FieldFluidRequest firstDrain,
        FieldFluidRequest secondDrain,
        FieldFluidRequest thirdDrain,
        FieldFluidRequest fourDrain,
        FieldFluidRequest runningOutputTotal
) {
}
