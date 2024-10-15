package com.azu.hospital.utils.generalRoute;

import com.azu.hospital.Patients.PatentEnum.CertificationEnum;
import com.azu.hospital.Patients.PatentEnum.IraqiProvinceEnum;
import com.azu.hospital.Patients.PatentEnum.ReligionEnum;

import com.azu.hospital.ph.StockMangment.math.money.Currency;
import com.azu.hospital.ph.mediciens.type.MedicineType;
import com.azu.hospital.utils.enums.*;
import com.azu.hospital.utils.enums.bottles.BloodGroupEnum;
import com.azu.hospital.utils.enums.lab.LabSpots;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin

public class GeneralController {


    @GetMapping("/currencies") // TODO: 9/26/2023 change the route in postman
    public List<String> getCurrencies() {
        return Arrays.stream(Currency.values())
                .map(Currency::getSymbol)
                .collect(Collectors.toList());
    }

    @GetMapping("/martial_status")
    public List<String> getMartialStatus(){
        return Arrays.stream(EnumMartialStatus.values())
               .map(EnumMartialStatus::toString)
               .collect(Collectors.toList());
    }

    @GetMapping("/relation_status")
    public List<String> getReligion(){
        return Arrays.stream(ReligionEnum.values())
              .map(ReligionEnum::getName)
              .collect(Collectors.toList());
    }

    @GetMapping("/iraqi_province")
    public List<String> getIraqiProvince(@RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE, defaultValue = "en") String language) {
        Locale locale = Locale.forLanguageTag(language);
        return Arrays.stream(IraqiProvinceEnum.values())
                .map(enumValue -> enumValue.getName(locale))
                .collect(Collectors.toList());
    }

    @GetMapping("/certifications")
    public List<String> getCertifications(){
        return Arrays.stream(CertificationEnum.values())
                .map(CertificationEnum::getName)
                .collect(Collectors.toList());
    }

    @GetMapping("/devices_type")
    public List<String> getDevice(){
        return Arrays.stream(DeviceType.values())
                .map(DeviceType::getName)
                .collect(Collectors.toList());
    }

    @GetMapping("/medicine_type")
    public List<String> getAllMedicineType(){
        return Arrays.stream(MedicineType.values())
                .map(MedicineType::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/blood-group")
    public List<String> getAllBloodGroup(){
        return Arrays.stream(BloodGroupEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/storage_type_for_tube_lab")
    public List<String> getAllLabTubeStorageType(){
        return Arrays.stream(LabTubeStorageType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/prices-item-type")
    public List<String> getAllEnumItemType(){
        return Arrays.stream(EnumItemType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/lab-spot-type")
    public List<String> getAllEnumLabSpotType(){
        return Arrays.stream(LabSpots.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/anesthesia-types")
    public List<String> getAllAnesthesiaTypes() {
        return Arrays.stream(AnesthesiaType.values())
                .map(AnesthesiaType::getName)
                .collect(Collectors.toList());
    }


 @GetMapping("/administration-route")
 public List<String> getAdministrationRoutes(@RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE, defaultValue = "en") String language) {
     Locale locale = Locale.forLanguageTag(language);
     return Arrays.stream(AdministrationRoute.values())
             .map(enumValue -> enumValue.getName(locale))
             .collect(Collectors.toList());
 }

    @GetMapping("/doctor-role")
    public List<String> getAllDoctorRole() {
        return Arrays.stream(EnumDoctorRole.values())
                .map(EnumDoctorRole::getName)
                .collect(Collectors.toList());
    }

//    @GetMapping("/nurse-role")
//    public List<String> getAllNurseRole() {
//        return Arrays.stream(EnumNurseRole.values())
//                .map(EnumNurseRole::getName)
//                .collect(Collectors.toList());
//    }

    @GetMapping("/permanent-role")
    public List<String> getAllPermanentRole() {
        return Arrays.stream(EnumPermanentRole.values())
                .map(EnumPermanentRole::getRoleName)
                .collect(Collectors.toList());
    }

    @GetMapping("/user-role")
    public List<String> getAllUserRole() {
        return Arrays.stream(EnumUsersRole.values())
                .map(EnumUsersRole::getName)
                .collect(Collectors.toList());
    }

}
