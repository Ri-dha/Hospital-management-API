package com.azu.hospital.exceptions.validators;

import com.azu.hospital.exceptions.DateTimeExceptions;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
@Component
public class DateValidations {

    public void changeDate( String expDate){
        LocalDate newExpDate = LocalDate.parse(expDate);
        int newExpDate1 = Math.toIntExact(ChronoUnit.DAYS.between(LocalDate.now(), newExpDate));
        if(newExpDate1 <= 30 && newExpDate1 != 0){
            throw new DateTimeExceptions("Item is Nearly expiry " + newExpDate1 + " Days " + "The Expiry Date " + newExpDate);
        }else if (newExpDate1 <= 0){
            throw new DateTimeExceptions("Item is expiry " + newExpDate);
        }
    }
}
