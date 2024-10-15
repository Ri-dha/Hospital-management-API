package com.azu.hospital.Patients.Patient.request;


import com.azu.hospital.Validator.DateValidator.DatePattern;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.i18n.LocaleContextHolder;


import java.beans.Transient;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
@Setter
@Embeddable
public class PatientDate {

    @NotNull(message = "birth date must not be null")
    @DatePattern(message = "Birth date has Invalid date format. Expected format: yyyy-MM-dd")
    private String birthDate;

    @NotNull(message = "Admission date must not be null")
    @DatePattern(message = "Admission date has Invalid date format. Expected format: yyyy-MM-dd")
    private String admissionDate;


    private Integer age;


    public PatientDate() {
    }

    public PatientDate(
            String birthDate,
            String admissionDate,
            Integer age
    ) {
        this.birthDate = birthDate;
        this.admissionDate = admissionDate;
        this.age = age;
    }

    public String getAge() {
        return calculateAndSetAge();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private String calculateAndSetAge() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthdate = LocalDate.parse(birthDate, formatter);
        Period agePeriod = calculateAge(birthdate);
        Locale locale = LocaleContextHolder.getLocale(); // Get the locale from the current thread
        String ageString = "";
        ageString += agePeriod.getYears() + " " + AgeUnit.YEARS.getName(locale) + ", ";
        ageString += agePeriod.getMonths() + " " + AgeUnit.MONTHS.getName(locale) + ", ";
        ageString += agePeriod.getDays() + " " + AgeUnit.DAYS.getName(locale);
        return ageString;
    }

    public  Period calculateAge(LocalDate birthdate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthdate, currentDate);
    }
}