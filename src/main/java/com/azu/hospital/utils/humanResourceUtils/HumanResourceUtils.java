package com.azu.hospital.utils.humanResourceUtils;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class HumanResourceUtils {

    public static final String APPOINTMENT_TIME_SUFFIX = "T10:30:00Z";

    public static final List<String> ALL_MONTHS = Arrays.asList(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    );


    public static List<String> getExistsMonth(List<MonthAmountDto> bonusDtoList){
        return bonusDtoList.stream()
                .map(MonthAmountDto::getMonth)
                .toList();
    }


    public static List<MonthAmountDto> getMonthAmountDtos(List<MonthAmountDto> DtoList) {
        List<String> existingMonths = getExistsMonth(DtoList);

        for (String month : ALL_MONTHS) {
            if (!existingMonths.contains(month)) {
                DtoList.add(new MonthAmountDto(month, 0L));
            }
        }

        DtoList.sort(Comparator.comparingInt(o -> ALL_MONTHS.indexOf(o.getMonth())));


        return DtoList;
    }
}
