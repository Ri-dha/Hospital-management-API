package com.azu.hospital.Patients.charts.nursingAssessment.services;

import com.azu.hospital.Patients.charts.nursingAssessment.entity.NursingAssessmentChart;
import com.azu.hospital.Patients.charts.nursingAssessment.dao.NursingAssessmentChartDao;
import com.azu.hospital.Patients.charts.nursingAssessment.request.NursingAssessmentChartRequest;
import com.azu.hospital.Patients.charts.nursingAssessment.utils.NursingAssessmentRequestToChart;
import com.azu.hospital.Patients.charts.physical_assment.*;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import com.azu.hospital.utils.return_id.IdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service("NursingChartUpdateService")
public class NursingAssessmentUpdateServices {

    private final NursingAssessmentChartDao assessmentChartDao;

    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public NursingAssessmentUpdateServices(
            @Qualifier("NursingAssessmentJpa") NursingAssessmentChartDao assessmentChartDao, ExceptionsMessageReturn messageReturn
    ) {
        this.assessmentChartDao = assessmentChartDao;
        this.messageReturn = messageReturn;
    }

    public IdResponse updateNursingChart(
            NursingAssessmentChartRequest request,
            Long chartId
    ) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        NursingAssessmentChart existsChart = assessmentChartDao
                .findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("NursingAssessmentChartNotFound")+" "+chartId

                        )
                );

        NursingAssessmentChart newChart = NursingAssessmentRequestToChart.getNursingAssessmentChartPrivate(request);
        compareMethodExistChartAndUpdateCart(existsChart, newChart);

        newChart.setPatient(existsChart.getPatient());
        NursingAssessmentChart savedChart = assessmentChartDao.updateExistChart(newChart);

        return new DtoForReturnIdOnly(savedChart.getId());
    }


    private static void compareMethodExistChartAndUpdateCart(
            NursingAssessmentChart existsChart,
            NursingAssessmentChart newChart
    ) {
        levelOfConcisonesCheckMethod(existsChart, newChart);
        mentalStatusCheckUpdateMethod(existsChart, newChart);
        mentailStateMethod(existsChart, newChart);
        eyesMethod(existsChart, newChart);
        earsMethod(existsChart, newChart);
        mouthMethod(existsChart, newChart);
        noseMethod(existsChart, newChart);
        hairMethod(existsChart, newChart);
        neckMethod(existsChart, newChart);
        skinMethod(existsChart, newChart);
        chestMethod(existsChart, newChart);
        abdomentMethod(existsChart, newChart);
        upperExterminiesMethod(existsChart, newChart);
        lowerExterminiteMethod(existsChart, newChart);
        if (newChart.getNote() == null){
            newChart.setNote(existsChart.getNote());
        }
        if (newChart.getPainLevel() == null){
            newChart.setPainLevel(existsChart.getPainLevel());
        }
    }

    private static void lowerExterminiteMethod(NursingAssessmentChart existsChart, NursingAssessmentChart newChart) {
        if (newChart.getLowerExtremities() == null) {
            newChart.setLowerExtremities(existsChart.getLowerExtremities());
        }else{
            LowerExtremities newLowerExtremities = newChart.getLowerExtremities();
            LowerExtremities existsLowerExtremities = existsChart.getLowerExtremities();
            if(newLowerExtremities.getPedalPush() == null){
                newLowerExtremities.setPedalPush(existsLowerExtremities.getPedalPush());
            }
            if(newLowerExtremities.getEdema() == null){
                newLowerExtremities.setEdema(existsLowerExtremities.getEdema());
            }
            if(newLowerExtremities.getLowerRageOfMotion() == null){
                newLowerExtremities.setLowerRageOfMotion(existsLowerExtremities.getLowerRageOfMotion());
            }
            if(newLowerExtremities.getCapillaryRefillTime() == null){
                newLowerExtremities.setCapillaryRefillTime(existsLowerExtremities.getCapillaryRefillTime());
            }
            if(newLowerExtremities.getDorsalisPedisPulse() == null){
                newLowerExtremities.setDorsalisPedisPulse(existsLowerExtremities.getDorsalisPedisPulse());
            }
            if(newLowerExtremities.getPosteriorTibialPulse() == null){
                newLowerExtremities.setPosteriorTibialPulse(existsLowerExtremities.getPosteriorTibialPulse());
            }

        }
    }

    private static void upperExterminiesMethod(NursingAssessmentChart existsChart, NursingAssessmentChart newChart) {
        if (newChart.getUpperExtremities() == null) {
            newChart.setUpperExtremities(existsChart.getUpperExtremities());
        }else{
            UpperExtremities newUpperExtremities = newChart.getUpperExtremities();
            UpperExtremities existsUpperExtremities = existsChart.getUpperExtremities();
            if(newUpperExtremities.getShoulderShrug() == null){
                newUpperExtremities.setShoulderShrug(existsUpperExtremities.getShoulderShrug());
            }
            if(newUpperExtremities.getGripStrength() == null){
                newUpperExtremities.setGripStrength(existsUpperExtremities.getGripStrength());
            }
            if(newUpperExtremities.getRageOfMotion() == null){
                newUpperExtremities.setRageOfMotion(existsUpperExtremities.getRageOfMotion());
            }
            if(newUpperExtremities.getCapillaryRefill() == null){
                newUpperExtremities.setCapillaryRefill(existsUpperExtremities.getCapillaryRefill());
            }
            if(newUpperExtremities.getRadialPulse() == null){
                newUpperExtremities.setRadialPulse(existsUpperExtremities.getRadialPulse());
            }
        }
    }

    private static void abdomentMethod(NursingAssessmentChart existsChart, NursingAssessmentChart newChart) {
        if (newChart.getAbdomen() == null) {
            newChart.setAbdomen(existsChart.getAbdomen());
        }else{
            Abdomen newAbdomen = newChart.getAbdomen();
            Abdomen existsAbdomen = existsChart.getAbdomen();
            if(newAbdomen.getAbdomenInspection() == null){
                newAbdomen.setAbdomenInspection(existsAbdomen.getAbdomenInspection());
            }
            if(newAbdomen.getBowelSounds() == null){
                newAbdomen.setBowelSounds(existsAbdomen.getBowelSounds());
            }
            if(newAbdomen.getPalpation() == null){
                newAbdomen.setPalpation(existsAbdomen.getPalpation());
            }
        }
    }

    private static void chestMethod(NursingAssessmentChart existsChart, NursingAssessmentChart newChart) {
        if (newChart.getChest() == null) {
            newChart.setChest(existsChart.getChest());
        }else{
            Chest newChest = newChart.getChest();
            Chest existsChest = existsChart.getChest();
            if(newChest.getInspection() == null){
                newChest.setInspection(existsChest.getInspection());
            }
            if(newChest.getRythm() == null){
                newChest.setRythm(existsChest.getRythm());
            }
            if(newChest.getHeartSounds() == null){
                newChest.setHeartSounds(existsChest.getHeartSounds());
            }
            if(newChest.getApicalHeartRate() == null){
                newChest.setApicalHeartRate(existsChest.getApicalHeartRate());
            }
            if(newChest.getBreathSounds() == null){
                newChest.setBreathSounds(existsChest.getBreathSounds());
            }
            if(newChest.getRespiratoryRythm() == null){
                newChest.setRespiratoryRythm(existsChest.getRespiratoryRythm());
            }
            if(newChest.getDepth() == null){
                newChest.setDepth(existsChest.getDepth());
            }
        }
    }

    private static void skinMethod(NursingAssessmentChart existsChart, NursingAssessmentChart newChart) {
        if (newChart.getSkin() == null){
            newChart.setSkin(existsChart.getSkin());
        }else{
            Skin newSkin = newChart.getSkin();
            Skin existsSkin = existsChart.getSkin();
            if(newSkin.getTemperature() == null){
                newSkin.setTemperature(existsSkin.getTemperature());
            }
            if(newSkin.getMoisture() == null){
                newSkin.setMoisture(existsSkin.getMoisture());
            }
            if(newSkin.getTurgor() == null){
                newSkin.setTurgor(existsSkin.getTurgor());
            }
            if(newSkin.getColor() == null){
                newSkin.setColor(existsSkin.getColor());
            }
            if(newSkin.getWound() == null){
                newSkin.setWound(existsSkin.getWound());
            }
            if(newSkin.getPressureUlcer() == null){
                newSkin.setPressureUlcer(existsSkin.getPressureUlcer());
            }
        }
    }

    private static void neckMethod(NursingAssessmentChart existsChart, NursingAssessmentChart newChart) {
        if (newChart.getNeck() == null) {
            newChart.setNeck(existsChart.getNeck());
        }else{
            Neck newNeck = newChart.getNeck();
            Neck existsNeck = existsChart.getNeck();
            if(newNeck.getSymmetry() == null){
                newNeck.setSymmetry(existsNeck.getSymmetry());
            }
            if(newNeck.getRoundConvex() == null){
                newNeck.setRoundConvex(existsNeck.getRoundConvex());
            }
            if(newNeck.getRangeOfMotion() == null){
                newNeck.setRangeOfMotion(existsNeck.getRangeOfMotion());
            }
            if(newNeck.getJugularVeinDistension() == null){
                newNeck.setJugularVeinDistension(existsNeck.getJugularVeinDistension());
            }
            if(newNeck.getLumpsBumps() == null){
                newNeck.setLumpsBumps(existsNeck.getLumpsBumps());
            }
        }
    }

    private static void hairMethod(NursingAssessmentChart existsChart, NursingAssessmentChart newChart) {
        if (newChart.getHair() == null) {
            newChart.setHair(existsChart.getHair());
        }else{
            Hair newHair = newChart.getHair();
            Hair existsHair = existsChart.getHair();
            if(newHair.getClean() == null){
                newHair.setClean(existsHair.getClean());
            }
            if(newHair.getDirty() == null){
                newHair.setDirty(existsHair.getDirty());
            }
            if(newHair.getNeatlyGroomed() == null){
                newHair.setNeatlyGroomed(existsHair.getNeatlyGroomed());
            }
            if(newHair.getThickFull() == null){
                newHair.setThickFull(existsHair.getThickFull());
            }
            if(newHair.getSparseHairLoss() == null){
                newHair.setSparseHairLoss(existsHair.getSparseHairLoss());
            }
            if(newHair.getEvenlyDistributed() == null){
                newHair.setEvenlyDistributed(existsHair.getEvenlyDistributed());
            }
            if(newHair.getUnevenlyDistributed() == null){
                newHair.setUnevenlyDistributed(existsHair.getUnevenlyDistributed());
            }
        }
    }

    private static void noseMethod(NursingAssessmentChart existsChart, NursingAssessmentChart newChart) {
        if (newChart.getNose() == null) {
            newChart.setNose(existsChart.getNose());
        }else{
            Nose newNose = newChart.getNose();
            Nose existsNose = existsChart.getNose();
            if(newNose.getMidline() == null){
                newNose.setMidline(existsNose.getMidline());
            }
            if(newNose.getSkinIntact() == null){
                newNose.setSkinIntact(existsNose.getSkinIntact());
            }
            if(newNose.getNoseSymmetrical() == null){
                newNose.setNoseSymmetrical(existsNose.getNoseSymmetrical());
            }
            if(newNose.getLesions() == null){
                newNose.setLesions(existsNose.getLesions());
            }
            if(newNose.getCongestion() == null){
                newNose.setCongestion(existsNose.getCongestion());
            }
            if(newNose.getSneezing() == null){
                newNose.setSneezing(existsNose.getSneezing());
            }
        }
    }

    private static void mouthMethod(NursingAssessmentChart existsChart, NursingAssessmentChart newChart) {
        if (newChart.getMouth() == null) {
            newChart.setMouth(existsChart.getMouth());
        }else{
            Mouth newMouth = newChart.getMouth();
            Mouth existsMouth = existsChart.getMouth();
            if(newMouth.getMoist() == null){
                newMouth.setMoist(existsMouth.getMoist());
            }
            if(newMouth.getDry() == null){
                newMouth.setDry(existsMouth.getDry());
            }
            if(newMouth.getPink() == null){
                newMouth.setPink(existsMouth.getPink());
            }
            if(newMouth.getSores() == null){
                newMouth.setSores(existsMouth.getSores());
            }
        }
    }

    private static void earsMethod(NursingAssessmentChart existsChart, NursingAssessmentChart newChart) {
        if (newChart.getEars() == null) {
            newChart.setEars(existsChart.getEars());
        }else{
            Ears newEars = newChart.getEars();
            Ears existsEars = existsChart.getEars();
            if(newEars.getEarSymmetrical() == null){
                newEars.setEarSymmetrical(existsEars.getEarSymmetrical());
            }
            if(newEars.getEarwaxDischarge() == null){
                newEars.setEarwaxDischarge(existsEars.getEarwaxDischarge());
            }
            if(newEars.getEarsSkinIntact() == null){
                newEars.setEarsSkinIntact(existsEars.getEarsSkinIntact());
            }
            if(newEars.getLesion() == null){
                newEars.setLesion(existsEars.getLesion());
            }
            if(newEars.getHearingDevices() == null){
                newEars.setHearingDevices(existsEars.getHearingDevices());
            }
        }
    }

    private static void eyesMethod(NursingAssessmentChart existsChart, NursingAssessmentChart newChart) {
        if (newChart.getEyes() == null) {
            newChart.setEyes(existsChart.getEyes());
        }else {
            Eyes newEyes = newChart.getEyes();
            Eyes existsEyes = existsChart.getEyes();
            if(newEyes.getConjunctiva() == null){
                newEyes.setConjunctiva(existsEyes.getConjunctiva());
            }
            if(newEyes.getPupils() == null){
                newEyes.setPupils(existsEyes.getPupils());
            }
            if(newEyes.getSclera() == null){
                newEyes.setSclera(existsEyes.getSclera());
            }
            if(newEyes.getAbnormalDischarged() == null){
                newEyes.setAbnormalDischarged(existsEyes.getAbnormalDischarged());
            }
            if(newEyes.getAbnormalFinding() == null){
                newEyes.setAbnormalFinding(existsEyes.getAbnormalFinding());
            }
        }
    }

    private static void mentailStateMethod(NursingAssessmentChart existsChart, NursingAssessmentChart newChart) {
        if (newChart.getMentalState() == null) {
            newChart.setMentalState(existsChart.getMentalState());
        }else{
            MentalState newMentalState = newChart.getMentalState();
            MentalState existsMentalState = existsChart.getMentalState();
            if (newMentalState.getCalm() == null){
                newMentalState.setCalm(existsMentalState.getCalm());
            }
            if (newMentalState.getCooperative() == null){
                newMentalState.setCooperative(existsMentalState.getCooperative());
            }
            if (newMentalState.getUncooperative() == null){
                newMentalState.setUncooperative(existsMentalState.getUncooperative());
            }
            if (newMentalState.getPleasant() == null){
                newMentalState.setPleasant(existsMentalState.getPleasant());
            }
            if (newMentalState.getAnxious() == null){
                newMentalState.setAnxious(existsMentalState.getAnxious());
            }
            if (newMentalState.getAngry() == null){
                newMentalState.setAngry(existsMentalState.getAngry());
            }
            if (newMentalState.getWithdrawn() == null){
                newMentalState.setWithdrawn(existsMentalState.getWithdrawn());
            }
        }
    }

    private static void mentalStatusCheckUpdateMethod(NursingAssessmentChart existsChart, NursingAssessmentChart newChart) {
        if (newChart.getOrientation() == null) {
            newChart.setOrientation(existsChart.getOrientation());
        }else{
            Orientation newOrientation = newChart.getOrientation();
            Orientation existsingOrientation = existsChart.getOrientation();

            if(newOrientation.getPerson() == null){
                newOrientation.setPerson(existsingOrientation.getPerson());
            }
            if(newOrientation.getTime() == null){
                newOrientation.setTime(existsingOrientation.getTime());
            }
            if(newOrientation.getPlace() == null){
                newOrientation.setPlace(existsingOrientation.getPlace());
            }
            if(newOrientation.getSituation() == null){
                newOrientation.setSituation(existsingOrientation.getSituation());
            }
        }
    }

    private static void levelOfConcisonesCheckMethod(NursingAssessmentChart existsChart, NursingAssessmentChart newChart) {
        if (newChart.getLevelOfConsciousness() == null) {
            newChart.setLevelOfConsciousness(existsChart.getLevelOfConsciousness());
        }else {
            LevelOfConsciousness newLevel = newChart.getLevelOfConsciousness();
            LevelOfConsciousness existingLevel = existsChart.getLevelOfConsciousness();

            if (newLevel.getAwake() == null) {
                newLevel.setAwake(existingLevel.getAwake());
            }
            if (newLevel.getAlert() == null) {
                newLevel.setAlert(existingLevel.getAlert());
            }
            if (newLevel.getDrowsy() == null) {
                newLevel.setDrowsy(existingLevel.getDrowsy());
            }
            if (newLevel.getRestless() == null) {
                newLevel.setRestless(existingLevel.getRestless());
            }
            if (newLevel.getConfused() == null) {
                newLevel.setConfused(existingLevel.getConfused());
            }
            if (newLevel.getSedated() == null) {
                newLevel.setSedated(existingLevel.getSedated());
            }
        }
    }
}
