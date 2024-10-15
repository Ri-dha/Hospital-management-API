package com.azu.hospital.radiology;

import com.azu.hospital.lab_collection.request.DateTimeTest;
import com.azu.hospital.radiology.dto.GetCardNumberDto;
import com.azu.hospital.radiology.dto.MostUserDto;
import com.azu.hospital.radiology.external.dao.ExternalRadiologyTestDao;
import com.azu.hospital.radiology.internal.dao.InternalRadiologyTestDao;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

// TODO: 12/29/2023 all this class should be discus with me
@Service
public class RadiologyHomeService {
    private final ExternalRadiologyTestDao externalRadiologyTestDao;
    private final InternalRadiologyTestDao internalRadiologyTestDao;

    @Autowired
    public RadiologyHomeService(
            @Qualifier("externalRadiologyTestRepo") ExternalRadiologyTestDao externalRadiologyTestDao,
            @Qualifier("internalRadiologyTestRepo") InternalRadiologyTestDao internalRadiologyTestDao) {
        this.externalRadiologyTestDao = externalRadiologyTestDao;
        this.internalRadiologyTestDao = internalRadiologyTestDao;
    }

    public GetCardNumberDto getBlock() {

        List<RadiologyOrderState> allTestsStates = new ArrayList<>();
        allTestsStates.add(RadiologyOrderState.Accepted);
        allTestsStates.add(RadiologyOrderState.Waitting);
        allTestsStates.add(RadiologyOrderState.Received);
//        allTestsStates.add(RadiologyOrderState.Completed);


        List<RadiologyOrderState> newStates = new ArrayList<>();
        newStates.add(RadiologyOrderState.Accepted);
        newStates.add(RadiologyOrderState.Waitting);
        newStates.add(RadiologyOrderState.Received);


        List<RadiologyTypeEnum> allTypes = List.of(RadiologyTypeEnum.values());


        Long todayTest = externalRadiologyTestDao.countOfTests(newStates, allTypes) +
                internalRadiologyTestDao.countOfTests(newStates, allTypes);

        Long ctTest = externalRadiologyTestDao.countOfTests(allTestsStates, List.of(RadiologyTypeEnum.CT_Scan)) +
                internalRadiologyTestDao.countOfTests(allTestsStates, List.of(RadiologyTypeEnum.CT_Scan));

        Long mriTest = externalRadiologyTestDao.countOfTests(allTestsStates, List.of(RadiologyTypeEnum.MRI)) +
                internalRadiologyTestDao.countOfTests(allTestsStates, List.of(RadiologyTypeEnum.MRI));


        Long xRayTest = externalRadiologyTestDao.countOfTests(allTestsStates, List.of(RadiologyTypeEnum.XRay)) +
                internalRadiologyTestDao.countOfTests(allTestsStates, List.of(RadiologyTypeEnum.XRay));

        return new GetCardNumberDto(todayTest, ctTest, mriTest, xRayTest);
    }


    public List<DateTimeTest> dayTimeAnalysis() {

        List<DateTimeTest> intTest = getDateTimeTest(internalRadiologyTestDao.getDateTimeTest(LocalDate.now()));
        List<DateTimeTest> extTest = getDateTimeTest(externalRadiologyTestDao.getDateTimeTest(LocalDate.now()));


        List<DateTimeTest> mergedList = new ArrayList<>();
        mergedList.addAll(intTest);
        mergedList.addAll(extTest);

        Map<Integer, Long> hourCountMap = mergedList.stream()
                .collect(Collectors.toMap(DateTimeTest::getHour, DateTimeTest::getCount, Long::sum));

        List<DateTimeTest> result = hourCountMap.entrySet().stream()
                .map(entry -> new DateTimeTest(entry.getValue(), entry.getKey()))
                .collect(Collectors.toList());

        collectHourAndSort(result);

        return result;

    }


    private void collectHourAndSort(List<DateTimeTest> test) {
        for (int i = 0; i < 24; i++) {

            if (test.size() <= i || test.get(i).getHour() == null) {
                test.add(new DateTimeTest(0L, i));
            }
        }
        test.sort(Comparator.comparing(DateTimeTest::getHour));
    }


    private List<DateTimeTest> getDateTimeTest(List<DateTimeTest> data) {
        return data.stream()
                .map(test -> {
                    Long count = test.getCount();
                    Integer hour = test.getHour();
                    return new DateTimeTest(count, hour);
                })
                .collect(Collectors.toList());

    }

    public MostUserDto mostUse() {
        List<RadiologyOrderState> allTestsStates = new ArrayList<>();
        allTestsStates.add(RadiologyOrderState.Accepted);
        allTestsStates.add(RadiologyOrderState.Received);
        allTestsStates.add(RadiologyOrderState.Completed);

        Long mriTest = externalRadiologyTestDao.countOfTests(allTestsStates, List.of(RadiologyTypeEnum.MRI, RadiologyTypeEnum.MRIWithDye)) +
                internalRadiologyTestDao.countOfTests(allTestsStates, List.of(RadiologyTypeEnum.MRI, RadiologyTypeEnum.MRIWithDye));

        Long ctTest = externalRadiologyTestDao.countOfTests(allTestsStates, List.of(RadiologyTypeEnum.CT_Scan, RadiologyTypeEnum.CT_ScanWithDye)) +
                internalRadiologyTestDao.countOfTests(allTestsStates, List.of(RadiologyTypeEnum.CT_Scan, RadiologyTypeEnum.CT_ScanWithDye));

        Long xRayTest = externalRadiologyTestDao.countOfTests(allTestsStates, List.of(RadiologyTypeEnum.XRay, RadiologyTypeEnum.XRayWithDye)) +
                internalRadiologyTestDao.countOfTests(allTestsStates, List.of(RadiologyTypeEnum.XRay, RadiologyTypeEnum.XRayWithDye));

        return new MostUserDto(mriTest, ctTest, xRayTest);
    }
}