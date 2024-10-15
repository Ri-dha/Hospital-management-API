package com.azu.hospital.ultrasound;

import com.azu.hospital.lab_collection.request.DateTimeTest;
import com.azu.hospital.ultrasound.dto.GetUltrasoundHomeCard;
import com.azu.hospital.ultrasound.external.dao.ExternalUltrasoundTestDao;
import com.azu.hospital.ultrasound.internal.dao.InternalUltrasoundTestDao;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UltrasoundHomeService {
    private final ExternalUltrasoundTestDao externalUltrasoundTestDao;
    private final InternalUltrasoundTestDao internalUltrasoundTestDao;
    @Autowired
    public UltrasoundHomeService(
            @Qualifier("externalUltrasoundTestRepo") ExternalUltrasoundTestDao externalUltrasoundTestDao,
            @Qualifier("internalUltrasoundTestRepo") InternalUltrasoundTestDao internalUltrasoundTestDao) {
        this.externalUltrasoundTestDao = externalUltrasoundTestDao;
        this.internalUltrasoundTestDao = internalUltrasoundTestDao;
    }

    public GetUltrasoundHomeCard getBlock(){

        List<UltrasoundOrderState> allStates = new ArrayList<>();
        allStates.add(UltrasoundOrderState.Accepted);
        allStates.add(UltrasoundOrderState.Waitting);
        allStates.add(UltrasoundOrderState.Received);
        allStates.add(UltrasoundOrderState.Rejected);
        allStates.add(UltrasoundOrderState.Completed);


        List<UltrasoundOrderState> newStates = new ArrayList<>();
        newStates.add(UltrasoundOrderState.Accepted);
        newStates.add(UltrasoundOrderState.Waitting);
        newStates.add(UltrasoundOrderState.Received);


        Long todayTests = externalUltrasoundTestDao.countAllTests(newStates)+
                        internalUltrasoundTestDao.countAllTests(newStates);

        Long allTests = externalUltrasoundTestDao.countAllTests(allStates)+
                internalUltrasoundTestDao.countAllTests(allStates);

        return new GetUltrasoundHomeCard(todayTests , allTests);
    }


    public List<DateTimeTest> dayTimeAnalysis() {

        List<DateTimeTest> intTest = getDateTimeTest(internalUltrasoundTestDao.getDateTimeTest(LocalDate.now()));
        List<DateTimeTest> extTest = getDateTimeTest(externalUltrasoundTestDao.getDateTimeTest(LocalDate.now()));


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
