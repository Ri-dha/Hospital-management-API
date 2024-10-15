package com.azu.hospital.all_user_sevices.user_controller;

import com.azu.hospital.all_user_sevices.regestrations_request.AllUserRegistrationRequest;
import com.azu.hospital.all_user_sevices.specialist_service.HospitalSpecialty;
import com.azu.hospital.all_user_sevices.user_dto.UserDto;
import com.azu.hospital.all_user_sevices.user_sevices.UserAddServices;
import com.azu.hospital.all_user_sevices.user_sevices.UserService;
import com.azu.hospital.all_user_sevices.user_sevices.UserUpdateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "User" , description = "User Curd")
@RestController
@RequestMapping("api/v1/users")
@CrossOrigin
public class UserController {

    private final UserAddServices addServices;
    private final UserUpdateService updateService;
    private final UserService userService;

    @Autowired
    public UserController(
            @Qualifier("UserAddService") UserAddServices addServices,
            @Qualifier("UserUpdateService") UserUpdateService updateService,
            @Qualifier("UserService") UserService userService
    )
    {
        this.addServices = addServices;
        this.updateService = updateService;
        this.userService = userService;
    }


    @PostMapping(path = "/add", consumes = "multipart/form-data")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER', 'ADMINISTRATIVE_MANAGER')")
    public ResponseEntity<Void> addNewUser(@Valid @ModelAttribute AllUserRegistrationRequest request) throws IOException {
        addServices.createNewUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping(value = "/{userId}", consumes = "multipart/form-data")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER', 'ADMINISTRATIVE_MANAGER')")
    public void updateExistUser(
            @Valid
            @ModelAttribute AllUserRegistrationRequest request,
            @PathVariable(value = "userId") Long userId
    ) throws IOException {
        updateService.updateExistUser(request, userId);
    }


    @GetMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER', 'ADMINISTRATIVE_MANAGER', 'DOCTOR')")
    public ResponseEntity<?> getAllUsers(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String specialist,
            @RequestParam(required = false) String bloodGroup,
            @RequestParam(required = false) String mobile,
            @RequestParam(required = false) String gender
    ) {
        Page<UserDto> response = userService.getAllUsers(
                size,
                page,
                username,
                email,
                specialist,
                bloodGroup,
                mobile,
                gender
        );
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER', 'ADMINISTRATIVE_MANAGER')")
    @GetMapping("/hospital_specialty")
    public List<String> getAllHospitalSpecialty(){
        return Arrays.stream(HospitalSpecialty.values())
                .map(HospitalSpecialty::getDisplayName)
                .collect(Collectors.toList());
    }
}
