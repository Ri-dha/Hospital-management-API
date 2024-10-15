package com.azu.hospital.all_user_sevices.user_sevices;

import com.azu.hospital.all_user_sevices.user_dao.UserDao;
import com.azu.hospital.all_user_sevices.user_dto.UserDto;
import com.azu.hospital.all_user_sevices.user_dto.UserDtoMapper;
import com.azu.hospital.all_user_sevices.user_entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service("UserService")
public class UserService {

    private final UserDao userDao;
    private final UserDtoMapper userDtoMapper;


    public UserService(
            @Qualifier("UserJpa") UserDao userDao,
            UserDtoMapper userDtoMapper) {
        this.userDao = userDao;
        this.userDtoMapper = userDtoMapper;
    }

    public Page<UserDto> getAllUsers(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "") String username,
            @RequestParam(required = false, defaultValue = "") String email,
            @RequestParam(required = false, defaultValue = "") String specialist,
            @RequestParam(required = false, defaultValue = "") String bloodGroup,
            @RequestParam(required = false, defaultValue = "") String mobile,
            @RequestParam(required = false, defaultValue = "") String gender
    ) {
        Pageable pageable = PageRequest.of(page, size);

        List<User> usersPage = userDao.findAllUsersBy(
                username,
                email,
                specialist,
                bloodGroup,
                mobile,
                gender
        );

        List<UserDto> dtoList = usersPage
                .stream()
                .map(userDtoMapper)
                .collect(Collectors.toList());

        Long count = userDao.countAllItems();
        return new PageImpl<>(dtoList, pageable, count);
    }





//    private List<String> getUserImage (Long userId){
//        User user = userDao.findUserById(userId)
//                .orElseThrow(
//                        ()-> new ResourceNotFoundException("User not found")
//
//                );
//        List<String> userImage = new ArrayList<String>();
//        userImage.add(user.getImage());
//        userImage.add(user.getBackMedicalId());
//        userImage.add(user.getBackPersonalId());
//        userImage.add(user.getFrontMedicalId());
//        userImage.add(user.getFrontPersonalId());
//        return userImage;
//    }




}
