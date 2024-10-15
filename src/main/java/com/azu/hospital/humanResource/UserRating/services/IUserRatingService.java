package com.azu.hospital.humanResource.UserRating.services;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.dto.BaseUserDto;
import com.azu.hospital.humanResource.UserRating.dto.UserRatingDto;
import com.azu.hospital.humanResource.UserRating.request.UserRatingRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserRatingService {
   void createUserRating(Long userId , UserRatingRequest request);

   Page<BaseUserDto> getRatingByDate(String date , Pageable pageable);
   UserRatingDto getLastRating(Long userId);

}
