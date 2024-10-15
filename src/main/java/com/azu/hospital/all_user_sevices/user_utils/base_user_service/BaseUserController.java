package com.azu.hospital.all_user_sevices.user_utils.base_user_service;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.dto.BaseUserDto;
import com.azu.hospital.all_user_sevices.user_utils.lookup.AppUserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/base_user")
@CrossOrigin
public class BaseUserController {
    private final BaseUserServices baseUserService;
    private final AppUserService appUserService;


    public BaseUserController(BaseUserServices baseUserService, AppUserService appUserService) {
        this.baseUserService = baseUserService;
        this.appUserService = appUserService;
    }

    @GetMapping("/token")
    public ResponseEntity<BaseUserDto> getAllUser(@RequestParam String token) {
        return ResponseEntity.ok(baseUserService.getUserByToken(token));
    }


    @PutMapping("/block_user")
    @ResponseStatus(HttpStatus.OK)
    public void blockUserAccount(@RequestParam("userId") Long userId) {
        baseUserService.blockUserAccount(userId);
    }


    @PutMapping("/un_block_user")
    @ResponseStatus(HttpStatus.OK)
    public void unBlockUserAccount(@RequestParam("userId") Long userId) {
        baseUserService.unBlockUserAccount(userId);
    }


    @GetMapping
    public ResponseEntity<?> getBaseUserById(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok(baseUserService.getDoctorById(userId));
    }


    @GetMapping("/emails")
    public ResponseEntity<?> getUsersByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(appUserService.findAppUserByEmail(email));
    }

    @GetMapping("/get-by-roles")
    public ResponseEntity<?> getAllUsersByRoles(
            @RequestParam("roleIds") List<Integer> roleIds,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(baseUserService.getListOfUsersByRoles(roleIds, pageable));
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllUsers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String roleName
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                baseUserService.getAllUsers(
                        search,
                        roleName,
                        pageable
                )
        );
    }
}
