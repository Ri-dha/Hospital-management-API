package com.azu.hospital.lab_collection;

import com.azu.hospital.lab_collection.dto.HomeCardDto;
import com.azu.hospital.lab_collection.external.dao.ExternalLabDao;
import com.azu.hospital.lab_collection.external.ext_tests_request.dao.ExtTestRequestDao;
import com.azu.hospital.lab_collection.internal.dao.InternalLabDao;
import com.azu.hospital.lab_collection.internal.int_tests_request.dao.IntTestRequestDao;
import com.azu.hospital.lab_collection.request.DateTimeTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LabHomeServices {

    private final InternalLabDao internalLabDao;
    private final ExternalLabDao externalLabDao;
    private final IntTestRequestDao intTestRequestDao;
    private final ExtTestRequestDao extTestRequestDao;

    @Autowired
    public LabHomeServices(
            @Qualifier("internalLabRepo") InternalLabDao internalLabDao,
            @Qualifier("externalLabRepo") ExternalLabDao externalLabDao,
            @Qualifier("internalTestRequestRepo") IntTestRequestDao intTestRequestDao,
            @Qualifier("testRequestRepo") ExtTestRequestDao extTestRequestDao
    ) {
        this.internalLabDao = internalLabDao;
        this.externalLabDao = externalLabDao;
        this.intTestRequestDao = intTestRequestDao;
        this.extTestRequestDao = extTestRequestDao;
    }


    public HomeCardDto getHomeBlocks(){

        Integer intSize = intTestRequestDao.getTop15By().size();

        return new HomeCardDto(
                internalLabDao.countAllInternalLab(),
                intSize,
                internalLabDao.countAll() + externalLabDao.countAll()
                );

    }


    public List<RequestCountData> mostCommonAnalysis(Integer maxSize) {

        List<RequestCountData> intData = intTestRequestDao.getTop15By();

        List<RequestCountData> extData = extTestRequestDao.getTop15By();

        List<RequestCountData> newData = new ArrayList<>(intData);
        newData.addAll(extData);

        Map<String, Long> nameToCountMap = newData.stream()
                .collect(Collectors.toMap(
                        RequestCountData::getLabName,
                        RequestCountData::getCount,
                        Long::sum
                ));

        List<RequestCountData> result = nameToCountMap.entrySet().stream()
                .map(entry ->
                        new RequestCountData(
                                entry.getKey(),
                                entry.getValue()
                        )).sorted(
                                Comparator.comparing(
                                        RequestCountData::getCount).
                                        reversed()
                ).collect(Collectors.toList());


        return result.subList(0 , Math.min(result.size(), maxSize));
    }


    public List<DateTimeTest> dayTimeAnalysis() {

        List<DateTimeTest> intTest = getDateTimeTest(internalLabDao.getDateTimeTest(LocalDate.now()));
        List<DateTimeTest> extTest = getDateTimeTest(externalLabDao.getDateTimeTest(LocalDate.now()));


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
        for (int i=0; i<24; i++) {

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

}
