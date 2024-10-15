package com.azu.hospital.Patients.charts.fluidBalance.request;

import com.azu.hospital.Patients.charts.fluidBalance.fluid_balance_classes.FieldFluidRequest;
import com.azu.hospital.Patients.charts.fluidBalance.fluid_balance_classes.FluidByIntravenous;
import com.azu.hospital.Patients.charts.fluidBalance.fluid_balance_classes.Oral;


public record FluidIntakeRequest(
        Long fluidBalanceInputId,
        String time,
        Oral oral,
        FluidByIntravenous fluidByIntravenous,
        FieldFluidRequest nasogastricFeed,
        FieldFluidRequest hourlyTotal,
        FieldFluidRequest runningInputTotal
) {
}
