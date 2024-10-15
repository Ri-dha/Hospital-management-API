package com.azu.hospital.Patients.PrematureBaby.utli;


import com.azu.hospital.Patients.Patient.request.AgeUnit;
import com.azu.hospital.Validator.DateValidator.DatePattern;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

@Getter
@Setter
@Embeddable
public class PrematureBabyDate {

    @DatePattern
    private String birthDate;
    private LocalTime birthTime;


    private String age;


    public PrematureBabyDate() {
    }


    public PrematureBabyDate(String birthDate, LocalTime birthTime, String age) {
        this.birthDate = birthDate;
        this.birthTime = birthTime;
        this.age = age;
    }

    public String getAge() {
        return calculateAndSetAge();
    }

    private String calculateAndSetAge() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthdate = LocalDate.parse(birthDate, formatter);
        // Combine the birth date with the birth time to create a LocalDateTime
        LocalDateTime birthDateTime = LocalDateTime.of(birthdate, birthTime);
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Calculate the difference in hours
        long totalHours = ChronoUnit.HOURS.between(birthDateTime, currentDateTime);
        long days = totalHours / 24;
        long hours = totalHours % 24;

        // Calculate the difference in months
        long months = ChronoUnit.MONTHS.between(birthDateTime, currentDateTime);
        long years = ChronoUnit.YEARS.between(birthDateTime, currentDateTime);

        // Determine the output format based on the elapsed time
        String ageString;
        if (totalHours < 24) {
            // If less than 24 hours have passed, return hours
            ageString = hours + " " + AgeUnit.HOURS.getName(LocaleContextHolder.getLocale());
        } else if (months < 1) {
            // If less than a month has passed, return days
            ageString = days + " " + AgeUnit.DAYS.getName(LocaleContextHolder.getLocale());
        } else {
            // If more than a month has passed, return months and years
            ageString = months + " " + AgeUnit.MONTHS.getName(LocaleContextHolder.getLocale()) + ", ";
            ageString += years + " " + AgeUnit.YEARS.getName(LocaleContextHolder.getLocale());
        }
        return ageString;
    }

    public Period calculateAge(LocalDateTime birthDateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Calculate difference in hours
        long totalHours = ChronoUnit.HOURS.between(birthDateTime, currentDateTime);

        // Calculate remaining hours within the current day
        long remainingHours = totalHours % 24;  // modulo by 24 to get hours within the day

        // Convert LocalDateTime to LocalDate for Period calculation
        LocalDate birthDate = birthDateTime.toLocalDate();
        LocalDate currentDate = currentDateTime.toLocalDate();

        // Handle case for birth within the same day (first 24 hours)
        if (birthDate.equals(currentDate)) {
            return Period.ofDays(0) // No days passed
                    .withYears(0)        // No years passed
                    .withMonths(0)       // No months passed
                    .withDays((int) remainingHours);  // Use remaining hours as days (age in hours)
        } else {
            // Calculate remaining period excluding hours (as before)
            Period remainingPeriod = Period.between(birthDate.plusDays(1), currentDate);

            return Period.ofDays((int) (totalHours - remainingHours) / 24) // Use remaining hours for days (excluding current day)
                    .withYears(remainingPeriod.getYears())
                    .withMonths(remainingPeriod.getMonths())
                    .withDays(remainingPeriod.getDays());
        }
    }


}
