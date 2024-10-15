package com.azu.hospital.Patients.charts.sedatedPatientAssessment.services;

import com.azu.hospital.Patients.charts.physical_assment.*;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.entities.SedatedPatientAssessment;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.dao.SedatedPatientAssessmentDao;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.request.SedatedPatientAssessmentRequest;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.utils.SedatedPatientAssessmentRequestToChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import com.azu.hospital.utils.return_id.IdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service("SedatedPatientAssessmentUpdateService")
public class SedatedPatientAssessmentUpdateService {
    private final ExceptionsMessageReturn messageReturn;

    private final SedatedPatientAssessmentDao chartDao;

    @Autowired
    public SedatedPatientAssessmentUpdateService(ExceptionsMessageReturn messageReturn, SedatedPatientAssessmentDao chartDao) {
        this.messageReturn = messageReturn;
        this.chartDao = chartDao;
    }

    public IdResponse updateExistsChart(Long chartId, SedatedPatientAssessmentRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        SedatedPatientAssessment existsChart = chartDao.findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("SedatedPatientAssessmentChartNotFound") + " " + chartId
                        )
                );

        SedatedPatientAssessment newChart = SedatedPatientAssessmentRequestToChart.requestToChart(request);

        compareMethodExistChartAndUpdateCart(existsChart, newChart);

        newChart.setPatient(existsChart.getPatient());

        SedatedPatientAssessment update = chartDao.updateExistsChart(newChart);

        return new DtoForReturnIdOnly(update.getId());
    }

    private void compareMethodExistChartAndUpdateCart(
            SedatedPatientAssessment existsChart,
            SedatedPatientAssessment newChart
    ) {
        if (newChart.getMedications() == null) {
            newChart.setMedications(existsChart.getMedications());
        }
        if (newChart.getPositiveProblems() == null) {
            newChart.setPositiveProblems(existsChart.getPositiveProblems());
        }
        if (newChart.getPreOpMeds() == null) {
            newChart.setPreOpMeds(existsChart.getPreOpMeds());
        }
        if (newChart.getAsa() == null) {
            newChart.setAsa(existsChart.getAsa());
        }

        updatePreProcedureMethod(existsChart, newChart);

        updatePreAnesthesiaStateMethod(existsChart, newChart);

        updatePatientSafetyMethod(existsChart, newChart);

        updateEyeCareMethod(existsChart, newChart);

        updateAnestheticTechniqueMethod(existsChart, newChart);

        updateMonitorsEquipmentMethod(existsChart, newChart);

        updateAirwayManagement(existsChart, newChart);

        if (newChart.getAnesthesiaStartTime() == null) {
            newChart.setAnesthesiaStartTime(existsChart.getAnesthesiaStartTime());
        }
        if (newChart.getAnesthesiaEndTime() == null) {
            newChart.setAnesthesiaEndTime(existsChart.getAnesthesiaEndTime());
        }
        if (newChart.getProcedureStartTime() == null) {
            newChart.setProcedureStartTime(existsChart.getProcedureStartTime());
        }
        if (newChart.getProcedureEndTime() == null) {
            newChart.setProcedureEndTime(existsChart.getProcedureEndTime());
        }
    }

    private static void updateAirwayManagement(SedatedPatientAssessment existsChart, SedatedPatientAssessment newChart) {
        if (newChart.getAirwayManagement() == null) {
            newChart.setAirwayManagement(existsChart.getAirwayManagement());
        } else {
            AirwayManagement existsAirwayManagement = existsChart.getAirwayManagement();
            AirwayManagement newAirwayManagement = newChart.getAirwayManagement();

            if (newAirwayManagement.getAirIntubation() == null) {
                newAirwayManagement.setAirIntubation(existsAirwayManagement.getAirIntubation());
            }
            if (newAirwayManagement.getTubeSize() == null) {
                newAirwayManagement.setTubeSize(existsAirwayManagement.getTubeSize());
            }
            if (newAirwayManagement.getBlade() == null) {
                newAirwayManagement.setBlade(existsAirwayManagement.getBlade());
            }
            if (newAirwayManagement.getAttempts() == null) {
                newAirwayManagement.setAttempts(existsAirwayManagement.getAttempts());
            }
            if (newAirwayManagement.getSecuredAt() == null) {
                newAirwayManagement.setSecuredAt(existsAirwayManagement.getSecuredAt());
            }
            if (newAirwayManagement.getBreathSounds() == null) {
                newAirwayManagement.setBreathSounds(existsAirwayManagement.getBreathSounds());
            }
            if (newAirwayManagement.getIsCuffed() == null) {
                newAirwayManagement.setIsCuffed(existsAirwayManagement.getIsCuffed());
            }
            if (newAirwayManagement.getIsCircle() == null) {
                newAirwayManagement.setIsCircle(existsAirwayManagement.getIsCircle());
            }
            if (newAirwayManagement.getIsNrb() == null) {
                newAirwayManagement.setIsNrb(existsAirwayManagement.getIsNrb());
            }
            if (newAirwayManagement.getIsMaskCase() == null) {
                newAirwayManagement.setIsMaskCase(existsAirwayManagement.getIsMaskCase());
            }
            if (newAirwayManagement.getIsViaLMA() == null) {
                newAirwayManagement.setIsViaLMA(existsAirwayManagement.getIsViaLMA());
            }
            if (newAirwayManagement.getIsNasalCannula() == null) {
                newAirwayManagement.setIsNasalCannula(existsAirwayManagement.getIsNasalCannula());
            }
            if (newAirwayManagement.getIsSimpleO2Mask() == null) {
                newAirwayManagement.setIsSimpleO2Mask(existsAirwayManagement.getIsSimpleO2Mask());
            }
        }
    }

    private static void updateMonitorsEquipmentMethod(SedatedPatientAssessment existsChart, SedatedPatientAssessment newChart) {
        if (newChart.getMonitorsEquipment() == null) {
            newChart.setMonitorsEquipment(existsChart.getMonitorsEquipment());
        } else {
            MonitorsEquipment existsMonitorsEquipment = existsChart.getMonitorsEquipment();
            MonitorsEquipment newMonitorsEquipment = newChart.getMonitorsEquipment();

            if (newMonitorsEquipment.getIsPrecordial() == null) {
                newMonitorsEquipment.setIsPrecordial(existsMonitorsEquipment.getIsPrecordial());
            }
            if (newMonitorsEquipment.getIsEsophogeal() == null) {
                newMonitorsEquipment.setIsEsophogeal(existsMonitorsEquipment.getIsEsophogeal());
            }
            if (newMonitorsEquipment.getIsLeft() == null) {
                newMonitorsEquipment.setIsLeft(existsMonitorsEquipment.getIsLeft());
            }
            if (newMonitorsEquipment.getIsRight() == null) {
                newMonitorsEquipment.setIsRight(existsMonitorsEquipment.getIsRight());
            }
            if (newMonitorsEquipment.getIsContinuousEcg() == null) {
                newMonitorsEquipment.setIsContinuousEcg(existsMonitorsEquipment.getIsContinuousEcg());
            }
            if (newMonitorsEquipment.getIsPulseOximeter() == null) {
                newMonitorsEquipment.setIsPulseOximeter(existsMonitorsEquipment.getIsPulseOximeter());
            }
            if (newMonitorsEquipment.getIsNgOgTube() == null) {
                newMonitorsEquipment.setIsNgOgTube(existsMonitorsEquipment.getIsNgOgTube());
            }
            if (newMonitorsEquipment.getIsEndTidalCO2() == null) {
                newMonitorsEquipment.setIsEndTidalCO2(existsMonitorsEquipment.getIsEndTidalCO2());
            }
            if (newMonitorsEquipment.getIsFluidWarmer() == null) {
                newMonitorsEquipment.setIsFluidWarmer(existsMonitorsEquipment.getIsFluidWarmer());
            }
            if (newMonitorsEquipment.getIsVLeadsEcg() == null) {
                newMonitorsEquipment.setIsVLeadsEcg(existsMonitorsEquipment.getIsVLeadsEcg());
            }
            if (newMonitorsEquipment.getIsGasAnalyzer() == null) {
                newMonitorsEquipment.setIsGasAnalyzer(existsMonitorsEquipment.getIsGasAnalyzer());
            }
            if (newMonitorsEquipment.getIsNerveStimulator() == null) {
                newMonitorsEquipment.setIsNerveStimulator(existsMonitorsEquipment.getIsNerveStimulator());
            }
            if (newMonitorsEquipment.getIsO2Sensor() == null) {
                newMonitorsEquipment.setIsO2Sensor(existsMonitorsEquipment.getIsO2Sensor());
            }
            if (newMonitorsEquipment.getIsFoleyCatheter() == null) {
                newMonitorsEquipment.setIsFoleyCatheter(existsMonitorsEquipment.getIsFoleyCatheter());
            }
            if (newMonitorsEquipment.getIsAirwayHumidifier() == null) {
                newMonitorsEquipment.setIsAirwayHumidifier(existsMonitorsEquipment.getIsAirwayHumidifier());
            }
            if (newMonitorsEquipment.getTemp() == null) {
                newMonitorsEquipment.setTemp(existsMonitorsEquipment.getTemp());
            }
            if (newMonitorsEquipment.getIvs() == null) {
                newMonitorsEquipment.setIvs(existsMonitorsEquipment.getIvs());
            }
            if (newMonitorsEquipment.getSite() == null) {
                newMonitorsEquipment.setSite(existsMonitorsEquipment.getSite());
            }
            if (newMonitorsEquipment.getGauge() == null) {
                newMonitorsEquipment.setGauge(existsMonitorsEquipment.getGauge());
            }
        }
    }

    private static void updateAnestheticTechniqueMethod(SedatedPatientAssessment existsChart, SedatedPatientAssessment newChart) {
        if (newChart.getAnestheticTechnique() == null) {
            newChart.setAnestheticTechnique(existsChart.getAnestheticTechnique());
        } else {
            AnestheticTechnique existsAnestheticTechnique = existsChart.getAnestheticTechnique();
            AnestheticTechnique newAnestheticTechnique = newChart.getAnestheticTechnique();

            if (newAnestheticTechnique.getIntravenous() == null) {
                newAnestheticTechnique.setIntravenous(existsAnestheticTechnique.getIntravenous());
            }
            if (newAnestheticTechnique.getInhalation() == null) {
                newAnestheticTechnique.setInhalation(existsAnestheticTechnique.getInhalation());
            }
            if (newAnestheticTechnique.getPreOxygenation() == null) {
                newAnestheticTechnique.setPreOxygenation(existsAnestheticTechnique.getPreOxygenation());
            }
            if (newAnestheticTechnique.getLta() == null) {
                newAnestheticTechnique.setLta(existsAnestheticTechnique.getLta());
            }
            if (newAnestheticTechnique.getRapidSequence() == null) {
                newAnestheticTechnique.setRapidSequence(existsAnestheticTechnique.getRapidSequence());
            }
            if (newAnestheticTechnique.getRectal() == null) {
                newAnestheticTechnique.setRectal(existsAnestheticTechnique.getRectal());
            }
            if (newAnestheticTechnique.getCricoidPressure() == null) {
                newAnestheticTechnique.setCricoidPressure(existsAnestheticTechnique.getCricoidPressure());
            }
            if (newAnestheticTechnique.getSpinal() == null) {
                newAnestheticTechnique.setSpinal(existsAnestheticTechnique.getSpinal());
            }
            if (newAnestheticTechnique.getEpidural() == null) {
                newAnestheticTechnique.setEpidural(existsAnestheticTechnique.getEpidural());
            }
            if (newAnestheticTechnique.getAxillary() == null) {
                newAnestheticTechnique.setAxillary(existsAnestheticTechnique.getAxillary());
            }
            if (newAnestheticTechnique.getBierBlock() == null) {
                newAnestheticTechnique.setBierBlock(existsAnestheticTechnique.getBierBlock());
            }
            if (newAnestheticTechnique.getAnkleBlock() == null) {
                newAnestheticTechnique.setAnkleBlock(existsAnestheticTechnique.getAnkleBlock());
            }
            if (newAnestheticTechnique.getPosition() == null) {
                newAnestheticTechnique.setPosition(existsAnestheticTechnique.getPosition());
            }
            if (newAnestheticTechnique.getLocal() == null) {
                newAnestheticTechnique.setLocal(existsAnestheticTechnique.getLocal());
            }
            if (newAnestheticTechnique.getDose() == null) {
                newAnestheticTechnique.setDose(existsAnestheticTechnique.getDose());
            }
            if (newAnestheticTechnique.getNeedle() == null) {
                newAnestheticTechnique.setNeedle(existsAnestheticTechnique.getNeedle());
            }
            if (newAnestheticTechnique.getAnestheticSite() == null) {
                newAnestheticTechnique.setAnestheticSite(existsAnestheticTechnique.getAnestheticSite());
            }
            if (newAnestheticTechnique.getAnestheticAttempts() == null) {
                newAnestheticTechnique.setAnestheticAttempts(existsAnestheticTechnique.getAnestheticAttempts());
            }
            if (newAnestheticTechnique.getOtherNotes() == null) {
                newAnestheticTechnique.setOtherNotes(existsAnestheticTechnique.getOtherNotes());
            }
        }
    }

    private static void updateEyeCareMethod(SedatedPatientAssessment existsChart, SedatedPatientAssessment newChart) {
        if (newChart.getEyeCare() == null) {
            newChart.setEyeCare(existsChart.getEyeCare());
        } else {
            EyeCare existsEyeCare = existsChart.getEyeCare();
            EyeCare newEyeCare = newChart.getEyeCare();

            if (newEyeCare.getOintment() == null) {
                newEyeCare.setOintment(existsEyeCare.getOintment());
            }
            if (newEyeCare.getSaline() == null) {
                newEyeCare.setSaline(existsEyeCare.getSaline());
            }
            if (newEyeCare.getShields() == null) {
                newEyeCare.setShields(existsEyeCare.getShields());
            }
            if (newEyeCare.getTaped() == null) {
                newEyeCare.setTaped(existsEyeCare.getTaped());
            }
            if (newEyeCare.getGoggles() == null) {
                newEyeCare.setGoggles(existsEyeCare.getGoggles());
            }
            if (newEyeCare.getPads() == null) {
                newEyeCare.setPads(existsEyeCare.getPads());
            }
            if (newEyeCare.getClosed() == null) {
                newEyeCare.setClosed(existsEyeCare.getClosed());
            }
        }
    }

    private static void updatePatientSafetyMethod(SedatedPatientAssessment existsChart, SedatedPatientAssessment newChart) {
        if (newChart.getPatientSafety() == null) {
            newChart.setPatientSafety(existsChart.getPatientSafety());
        } else {
            PatientSafety existsPatientSafety = existsChart.getPatientSafety();
            PatientSafety newPatientSafety = newChart.getPatientSafety();

            if (newPatientSafety.getAnesthesiaMachineChecked() == null) {
                newPatientSafety.setAnesthesiaMachineChecked(existsPatientSafety.getAnesthesiaMachineChecked());
            }
            if (newPatientSafety.getPressurePointsChecked() == null) {
                newPatientSafety.setPressurePointsChecked(existsPatientSafety.getPressurePointsChecked());
            }
            if (newPatientSafety.getSafetyBeltOn() == null) {
                newPatientSafety.setSafetyBeltOn(existsPatientSafety.getSafetyBeltOn());
            }
            if (newPatientSafety.getArmsTucked() == null) {
                newPatientSafety.setArmsTucked(existsPatientSafety.getArmsTucked());
            }
            if (newPatientSafety.getAxillaryRoll() == null) {
                newPatientSafety.setAxillaryRoll(existsPatientSafety.getAxillaryRoll());
            }
            if (newPatientSafety.getRestraints() == null) {
                newPatientSafety.setRestraints(existsPatientSafety.getRestraints());
            }
        }
    }

    private static void updatePreAnesthesiaStateMethod(SedatedPatientAssessment existsChart, SedatedPatientAssessment newChart) {
        if (newChart.getPreAnesthesiaState() == null) {
            newChart.setPreAnesthesiaState(existsChart.getPreAnesthesiaState());
        } else {
            PreAnesthesiaState existsPreAnesthesiaState = existsChart.getPreAnesthesiaState();
            PreAnesthesiaState newPreAnesthesiaState = newChart.getPreAnesthesiaState();

            if (newPreAnesthesiaState.getAwake() == null) {
                newPreAnesthesiaState.setAwake(existsPreAnesthesiaState.getAwake());
            }
            if (newPreAnesthesiaState.getApprehensive() == null) {
                newPreAnesthesiaState.setApprehensive(existsPreAnesthesiaState.getApprehensive());
            }
            if (newPreAnesthesiaState.getUncooperative() == null) {
                newPreAnesthesiaState.setUncooperative(existsPreAnesthesiaState.getUncooperative());
            }
            if (newPreAnesthesiaState.getAsleep() == null) {
                newPreAnesthesiaState.setAsleep(existsPreAnesthesiaState.getAsleep());
            }
            if (newPreAnesthesiaState.getConfused() == null) {
                newPreAnesthesiaState.setConfused(existsPreAnesthesiaState.getConfused());
            }
            if (newPreAnesthesiaState.getUnresponsive() == null) {
                newPreAnesthesiaState.setUnresponsive(existsPreAnesthesiaState.getUnresponsive());
            }
        }
    }

    private static void updatePreProcedureMethod(SedatedPatientAssessment existsChart, SedatedPatientAssessment newChart) {
        if (newChart.getPreProcedure() == null) {
            newChart.setPreProcedure(existsChart.getPreProcedure());
        } else {
            PreProcedure existsPreProcedure = existsChart.getPreProcedure();
            PreProcedure newPreProcedure = newChart.getPreProcedure();

            if (newPreProcedure.getIsPtIdentified() == null) {
                newPreProcedure.setIsPtIdentified(existsPreProcedure.getIsPtIdentified());
            }
            if (newPreProcedure.getIsConsent() == null) {
                newPreProcedure.setIsConsent(existsPreProcedure.getIsConsent());
            }
            if (newPreProcedure.getIsChartReviewed() == null) {
                newPreProcedure.setIsChartReviewed(existsPreProcedure.getIsChartReviewed());
            }
            if (newPreProcedure.getIsNpoSince() == null) {
                newPreProcedure.setIsNpoSince(existsPreProcedure.getIsNpoSince());
            }
        }
    }
}
