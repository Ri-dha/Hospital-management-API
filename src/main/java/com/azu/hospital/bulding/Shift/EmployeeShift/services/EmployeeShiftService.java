package com.azu.hospital.bulding.Shift.EmployeeShift.services;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.bulding.Shift.EmployeeShift.dao.EmployeeShiftDao;
import com.azu.hospital.bulding.Shift.EmployeeShift.dto.EmployeeShiftAnnalistDto;
import com.azu.hospital.bulding.Shift.EmployeeShift.dto.EmployeeShiftDto;
import com.azu.hospital.bulding.Shift.EmployeeShift.dto.EmployeeShiftDtoMapper;
import com.azu.hospital.bulding.Shift.EmployeeShift.entity.EmployeeShift;
import com.azu.hospital.bulding.Shift.EmployeeShift.request.AddUserToShiftRequest;
import com.azu.hospital.bulding.Shift.EmployeeShift.request.EmployeeShiftRequest;
import com.azu.hospital.bulding.Shift.EmployeeShift.request.EmployeeShiftUpdate;
import com.azu.hospital.bulding.Shift.dao.ShiftDao;
import com.azu.hospital.bulding.Shift.entity.Shift;
import com.azu.hospital.bulding.ward.dao.WardDao;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.Shift.ShiftType;
import jakarta.servlet.http.HttpServletRequest;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
public class EmployeeShiftService extends GenericBaseService {

    private final EmployeeShiftDao employeeShiftDao;

    private final EmployeeShiftDtoMapper mapper;

    private final ShiftDao shiftDao;

    private final BaseUserDao userDao;

    private final WardDao wardDao;





    public EmployeeShiftService(
            JwtService jwtService,
            HttpServletRequest request,
            @Qualifier("UserShiftDataJpa") EmployeeShiftDao nurseShiftDao,
            @Qualifier("employeeShiftDtoMapper") EmployeeShiftDtoMapper mapper,
            @Qualifier("ShiftDataJpa") ShiftDao shiftDao,
            BaseUserDao userDao,
            @Qualifier("wardRepo") WardDao wardDao) {
        super(jwtService, request);
        this.employeeShiftDao = nurseShiftDao;
        this.mapper = mapper;
        this.shiftDao = shiftDao;
        this.userDao = userDao;
        this.wardDao = wardDao;
    }

    private static String getDayName(Integer day) {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();

        LocalDate date = LocalDate.of(currentDate.getYear(), currentMonth, day);
        return date.format(DateTimeFormatter.ofPattern("EEEE"));

    }

    public void createNurseShift(EmployeeShiftRequest request) {

        Ward ward = wardDao.findWardById(request.wardId()).orElseThrow(
                () -> new ResourceNotFoundException("Ward not found")
        );

        List<BaseUser> users = userDao.getAllUsersByIdIn(request.usersIds());

        if (users.size() != request.usersIds().size()) {
            throw new ResourceNotFoundException("Nurse not found");
        }

        Shift shift = shiftDao.getShiftById(request.shiftId()).orElseThrow(
                () -> new ResourceNotFoundException("Shift not found")
        );

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        YearMonth currentYearMonth = YearMonth.of(currentYear, currentMonth);

        if (request.startDay() < 1 || request.startDay() > currentYearMonth.lengthOfMonth() || request.endDay() < 1 || request.endDay() > currentYearMonth.lengthOfMonth()) {
            throw new IllegalArgumentException("Start and end day must be between 1 and " + currentYearMonth.lengthOfMonth());
        }

        List<EmployeeShift> employeeShifts = new ArrayList<>();

        for (BaseUser user : users) {

            if (employeeShiftDao.existsByUserIdAndShiftId(user.getId(), request.shiftId())) {
                throw new BadRequestException("Shift already exists");
            }

            EmployeeShift employeeShift = new EmployeeShift();
            employeeShift.setUser(user);
            employeeShift.setShift(shift);
            employeeShift.setWard(ward);

            employeeShift.setShiftingDays(IntStream.rangeClosed(request.startDay(), request.endDay()).boxed().collect(Collectors.toList()));

            employeeShifts.add(employeeShift);
        }

        employeeShiftDao.createManyUsersShifts(employeeShifts);
    }


    public void updateNurseShift(EmployeeShiftUpdate request, Long id) {



        EmployeeShift employeeShift = employeeShiftDao.getUserShiftById(id).orElseThrow(
                () -> new ResourceNotFoundException("Nurse shift not found")
        );


        employeeShiftDao.updateUserShift(employeeShift);

        Boolean changed = false;

        if (request.shiftId() != null && !employeeShift.getShift().getId().equals(request.shiftId())) {
            Shift shift = shiftDao.getShiftById(request.shiftId()).orElseThrow(
                    () -> new ResourceNotFoundException("Shift not found")
            );

            employeeShift.setShift(shift);
            changed = true;
        }

        if (request.wardId() != null && !employeeShift.getWard().getWardId().equals(request.wardId())) {
            Ward ward = wardDao.findWardById(request.wardId()).orElseThrow(
                    () -> new ResourceNotFoundException("Ward not found")
            );

            employeeShift.setWard(ward);
            changed = true;
        }



        if (!changed)
            throw new BadRequestException("Nothing to update");


        employeeShiftDao.createNewUserShift(new EmployeeShift(
                        employeeShift.getUser(),
                        employeeShift.getShift(),
                        employeeShift.getWard()
        ));

    }

    public void addNurseToShift(AddUserToShiftRequest request) {

        BaseUser user = userDao.findById(request.userId()).orElseThrow(
                () -> new ResourceNotFoundException("user not found")
        );

        Shift shift = shiftDao.getShiftById(request.shiftId()).orElseThrow(
                () -> new ResourceNotFoundException("Shift not found")
        );

        Ward ward = wardDao.findWardById(request.wardId()).orElseThrow(
                () -> new ResourceNotFoundException("Ward not found")
        );

        if (employeeShiftDao.existsByUserIdAndShiftId(request.userId(), request.shiftId())) {
            throw new BadRequestException("Nurse already in this shift");
        }

        EmployeeShift nurseShift = new EmployeeShift();
        nurseShift.setUser(user);
        nurseShift.setShift(shift);
        nurseShift.setWard(ward);


        employeeShiftDao.createNewUserShift(nurseShift);

    }

    public void changeNurseShift(Long userId, Long shiftId, Long wardId) {

        BaseUser user = userDao.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user not found")
        );

        Shift shift = shiftDao.getShiftById(shiftId).orElseThrow(
                () -> new ResourceNotFoundException("Shift not found")
        );

        Ward ward = wardDao.findWardById(wardId).orElseThrow(
                () -> new ResourceNotFoundException("Ward not found")
        );

        if (!employeeShiftDao.existsByUserIdAndShiftId(userId, shiftId)) {
            throw new BadRequestException("User not in this shift");
        }

        EmployeeShift nurseShift = new EmployeeShift();
        nurseShift.setUser(user);
        nurseShift.setShift(shift);
        nurseShift.setWard(ward);


        employeeShiftDao.updateUserShift(nurseShift);

    }

    public Page<EmployeeShiftDto> getAllNurseShifts(Pageable pageable) {
        return employeeShiftDao.getAllUserShifts(pageable).map(mapper);

    }

    public List<EmployeeShiftAnnalistDto> getNurseShiftsByNurseId(Long userId, String date) {


        BaseUser user = userDao.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found")
        );

        List<EmployeeShift> nurseShifts = employeeShiftDao.getUserShiftsByNurseId(userId, LocalDate.parse(date));


        YearMonth yearMonth = YearMonth.of(LocalDate.parse(date).getYear(), LocalDate.parse(date).getMonth());

        List<EmployeeShiftAnnalistDto> nurseShiftAnnalistDtos = new ArrayList<>();






        boolean isNight = true;



        Integer pointerIncrease = 1;


        for (EmployeeShift nurseShift : nurseShifts) {
            Integer pointer =0;
            if (nurseShift.getShift().getShiftType().equals(ShiftType.NIGHT) || nurseShift.getShift().getShiftType().equals(ShiftType.EVENING)) {
                pointerIncrease = 2;
            } else {
                pointerIncrease = 1;
            }
            for (int i = 1; i <= yearMonth.lengthOfMonth(); i++) {
                if (pointer == i) {
                    if (!getDayName(i).equals("Friday") && !getDayName(i).equals("Saturday") && !getDayName(i).equals("Thursday")) {
                        nurseShiftAnnalistDtos.add(
                                new EmployeeShiftAnnalistDto(
                                        nurseShift.getWard().getName(),
                                        nurseShift.getShift().getFromTime(),
                                        nurseShift.getShift().getToTime(),
                                        yearMonth.getYear() + "-" + yearMonth.getMonthValue() + "-" + i,
                                        getDayName(i),
                                        user.getUsernameSpecial(),
                                        nurseShift.getShift().getName(),
                                        i
                                )
                        );
                    }


                    pointer += pointerIncrease;
                }

            }
        }


        nurseShiftAnnalistDtos = nurseShiftAnnalistDtos
                .stream()
                        .sorted(
                        Comparator.comparing(EmployeeShiftAnnalistDto::getSorted)
                                .thenComparing(EmployeeShiftAnnalistDto::getFromTime)
                )
                .toList();


        return nurseShiftAnnalistDtos;


    }

    public List<EmployeeShiftAnnalistDto> getEmployeeShiftsByUserId(Long userId, String date) {
//        BaseUser user = userDao.findById(userId).orElseThrow(
//                () -> new ResourceNotFoundException("User not found")
//        );
//
//        List<EmployeeShift> employeeShifts = employeeShiftDao.getUserShiftsByNurseId(userId, LocalDate.parse(date));
//
//        YearMonth yearMonth = YearMonth.of(LocalDate.parse(date).getYear(), LocalDate.parse(date).getMonth());
//
//        List<EmployeeShiftAnnalistDto> employeeShiftAnnalistDtos = new ArrayList<>();
//
//
//
//            for (int i = 1; i <= yearMonth.lengthOfMonth(); i += 1) {
//                    employeeShiftAnnalistDtos.add(
//                            new EmployeeShiftAnnalistDto(
//                                    employeeShift.getWard().getName(),
//                                    employeeShift.getShift().getFromTime(),
//                                    employeeShift.getShift().getToTime(),
//                                    yearMonth.getYear() + "-" + yearMonth.getMonthValue() + "-" + i,
//                                    getDayName(i),
//                                    user.getUsernameSpecial(),
//                                    employeeShift.getShift().getName(),
//                                    i
//                            )
//                    );
//                }
//
////        }
//        employeeShiftAnnalistDtos = employeeShiftAnnalistDtos.stream()
//                .sorted(Comparator.comparing(EmployeeShiftAnnalistDto::getSorted)
//                        .thenComparing(EmployeeShiftAnnalistDto::getFromTime))
//                .toList();
//
//        return employeeShiftAnnalistDtos;
        return null;
    }

}

