package com.azu.hospital.humanResource.UserRating.services;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.dto.BaseUserDto;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.dto.BaseUserDtoMapper;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.humanResource.UserRating.dao.UserRatingDao;
import com.azu.hospital.humanResource.UserRating.dto.UserRatingDto;
import com.azu.hospital.humanResource.UserRating.dto.UserRatingDtoMapper;
import com.azu.hospital.humanResource.UserRating.entity.UserRating;
import com.azu.hospital.humanResource.UserRating.request.UserRatingRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.azu.hospital.utils.humanResourceUtils.HumanResourceUtils.APPOINTMENT_TIME_SUFFIX;


@Service
public class UserRatingService implements IUserRatingService{

  private final UserRatingDao ratingDao;
  private final UserRatingDtoMapper mapper;
  private final BaseUserDtoMapper userMapper;
  private final BaseUserDao userDao;
  private final ExceptionsMessageReturn messageReturn;


  @Autowired
  public UserRatingService(
          UserRatingDao ratingDao,
          @Qualifier("userRatingDtoMapper") UserRatingDtoMapper mapper,
          @Qualifier("baseUserDtoMapper") BaseUserDtoMapper userMapper,
          @Qualifier("BaseUserJpa") BaseUserDao userDao, ExceptionsMessageReturn messageReturn) {
    this.ratingDao = ratingDao;
    this.mapper = mapper;
    this.userMapper = userMapper;
    this.userDao = userDao;
      this.messageReturn = messageReturn;
  }

  @Override
  public void createUserRating(Long userId, UserRatingRequest request) {
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
    BaseUser user = userDao.findById(userId).orElseThrow(
            () -> new ResourceNotFoundException(
                    messages.getString("resourceNotFound")
            ));

    Optional<UserRating> rate = ratingDao.getLastUserRating(user.getId(), Instant.now());

    if (rate.isEmpty()){
      UserRating newRate = new UserRating(request.rating());
      newRate.setUser(user);
      ratingDao.createUserRating(newRate);
    }else{
      throw  new BadRequestException(
                messages.getString("invalidDate")
      );
    }

  }

  @Override
  public Page<BaseUserDto> getRatingByDate(String date , Pageable pageable) {
    Instant newDate = Instant.parse(date + APPOINTMENT_TIME_SUFFIX);
    List<BaseUserDto> userDto = userDao.getAllByRating(newDate, pageable)
            .stream()
            .map(userMapper)
            .toList();

    return new PageImpl<>(userDto, pageable, pageable.getPageSize());
  }


  @Override
  public UserRatingDto getLastRating(Long userId){
    return ratingDao.getLastUserRating(userId,Instant.now())
            .map(mapper)
            .orElse(null);
  }

}

