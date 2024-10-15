package com.azu.hospital.humanResource.UserRating.dto;

import com.azu.hospital.humanResource.UserRating.entity.UserRating;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserRatingDtoMapper implements Function<UserRating, UserRatingDto> {
  @Override
  public UserRatingDto apply(UserRating rating) {
    return new UserRatingDto(
            rating.getUser().getId(),
            rating.getId(),
            rating.getRating(),
            rating.getCreatedAt()
    );
  }
}
