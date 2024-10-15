package com.azu.hospital.Patients.charts.fluidBalance.services;

import com.azu.hospital.Patients.charts.fluidBalance.dao.FluidBalanceDao;
import com.azu.hospital.Patients.charts.fluidBalance.dto.FluidOutputDto;
import com.azu.hospital.Patients.charts.fluidBalance.dto.FluidOutputDtoMapper;
import com.azu.hospital.Patients.charts.fluidBalance.entities.FluidBalanceChart;
import com.azu.hospital.Patients.charts.fluidBalance.entities.FluidOutput;
import com.azu.hospital.Patients.charts.fluidBalance.fluid_balance_classes.FieldFluidRequest;
import com.azu.hospital.Patients.charts.fluidBalance.request.FluidOutputRequest;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FluidOutputService {
    private final ExceptionsMessageReturn messageReturn;

    private final FluidBalanceDao chartDao;
    private final FluidOutputDtoMapper dtoMapper;

    public FluidOutputService(
            ExceptionsMessageReturn messageReturn, @Qualifier("FluidBalanceJpaDataAccess")
    FluidBalanceDao chartDao,
            FluidOutputDtoMapper dtoMapper
    ) {
        this.messageReturn = messageReturn;
        this.chartDao = chartDao;
        this.dtoMapper = dtoMapper;
    }


    public List<FluidOutputDto> getFluidOutputTable(
            Long chartId
    ) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        FluidBalanceChart chart = chartDao.findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("FluidBalanceChartNotFound") + " " + chartId)
                );

        List<FluidOutputDto> response = new ArrayList<>();

        for (FluidOutput input : chart.getOutputs()) {
            response.add(dtoMapper.chartToDto(input));
        }
        return response;
    }

    public Map<String, Object> addNewFluidOutput(
            Long chartId,
            FluidOutputRequest request
    ) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Map<String, Object> response = new HashMap<>();

        FluidBalanceChart chart = chartDao.findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("FluidBalanceChartNotFound") + " " + chartId
                        ));

        List<FluidOutput> outputs = new ArrayList<>(chart.getOutputs());

        FluidOutput fluidOutput = new FluidOutput(
                request.time(),
                request.urine(),
                request.bowelOrStoma(),
                request.firstDrain(),
                request.secondDrain(),
                request.thirdDrain(),
                request.fourDrain(),
                request.runningOutputTotal()
        );

        outputs.add(fluidOutput);

        chart.setOutputs(outputs);

        FluidOutput updatedInput = chartDao.createNewOutputRow(fluidOutput);

        response.put("id", updatedInput.getFluidOutputId());

        return response;
    }

    public Map<String, Object> updateFluidInput(
            Long rowId,
            Long chartId,
            FluidOutputRequest request
    ) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Map<String, Object> response = new HashMap<>();

        FluidBalanceChart chart = chartDao.findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("FluidBalanceChartNotFound") + " " + chartId)
                );

        FluidOutput existsRow = chartDao.getOutputRowById(rowId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("FluidOutputNotFound") + " " + rowId)
                );

        FluidOutput newRow = new FluidOutput(
                request.time(),
                request.urine(),
                request.bowelOrStoma(),
                request.firstDrain(),
                request.secondDrain(),
                request.thirdDrain(),
                request.fourDrain(),
                request.runningOutputTotal()
        );

        chart.getOutputs().add(newRow);

        compareAndUpdateMethod(existsRow, newRow);

        FluidOutput updated = chartDao.updateExistsOutputRow(newRow);

        response.put("id", updated.getFluidOutputId());

        return response;
    }

    private void compareAndUpdateMethod(
            FluidOutput existsRow,
            FluidOutput newRow
    ) {
        if (newRow.getTime() == null) {
            newRow.setTime(existsRow.getTime());
        }

        if (newRow.getUrine() == null) {
            newRow.setUrine(existsRow.getUrine());
        } else {
            updateField(newRow.getUrine(), existsRow.getUrine());
        }
        if (newRow.getBowelOrStoma() == null) {
            newRow.setBowelOrStoma(existsRow.getBowelOrStoma());
        } else {
            updateField(newRow.getBowelOrStoma(), existsRow.getBowelOrStoma());
        }

        if (newRow.getFirstDrain() == null) {
            newRow.setFirstDrain(existsRow.getFirstDrain());
        } else {
            updateField(newRow.getFirstDrain(), existsRow.getFirstDrain());
        }

        if (newRow.getSecondDrain() == null) {
            newRow.setSecondDrain(existsRow.getSecondDrain());
        } else {
            updateField(newRow.getSecondDrain(), existsRow.getSecondDrain());
        }

        if (newRow.getThirdDrain() == null) {
            newRow.setThirdDrain(existsRow.getThirdDrain());
        } else {
            updateField(newRow.getThirdDrain(), existsRow.getThirdDrain());
        }

        if (newRow.getFourDrain() == null) {
            newRow.setFourDrain(existsRow.getFourDrain());
        } else {
            updateField(newRow.getFourDrain(), existsRow.getFourDrain());
        }
        if (newRow.getRunningOutputTotal() == null) {
            newRow.setRunningOutputTotal(existsRow.getRunningOutputTotal());
        } else {
            updateField(newRow.getRunningOutputTotal(), existsRow.getRunningOutputTotal());
        }
    }

    private void updateField(FieldFluidRequest newField, FieldFluidRequest existsField) {
        if (newField.getValue() == null) {
            newField.setValue(existsField.getValue());
        }
        if (newField.getTotal() == null) {
            newField.setTotal(existsField.getTotal());
        }
    }
}
