package com.azu.hospital.humanResource.UserRating.dao;


import com.azu.hospital.humanResource.UserRating.entity.UserRating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.Optional;

public interface UserRatingDao {

  Optional<UserRating> getLastUserRating(Long userId , Instant date);

  void createUserRating(UserRating rating);



}
