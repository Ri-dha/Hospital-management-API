package com.azu.hospital.Patients.charts.physical_assment;

import com.azu.hospital.Patients.charts.physical_assessment_enm.Fluid;
import com.azu.hospital.Patients.charts.physical_assessment_enm.IVSitesEnum;
import com.azu.hospital.Patients.charts.physical_assessment_enm.IVSitesLocationEnum;
import jakarta.persistence.ElementCollection;
import lombok.Data;

import java.util.Set;

@Data
public class IVSite {

    @ElementCollection
    private Set<Fluid> fluid;

    @ElementCollection
    private Set<IVSitesEnum> siteType;

    @ElementCollection
    private Set<IVSitesLocationEnum> siteLocation;

    public IVSite() {
    }

    public IVSite(Set<Fluid> fluid, Set<IVSitesEnum> siteType, Set<IVSitesLocationEnum> siteLocation) {
        this.fluid = fluid;
        this.siteType = siteType;
        this.siteLocation = siteLocation;
    }
}
