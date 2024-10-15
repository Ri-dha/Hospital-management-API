package com.azu.hospital.humanResource.UserRating.dao;

import com.azu.hospital.humanResource.UserRating.entity.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;


@Repository("RatingJpa")
public class UserRatingJpaDataAccess implements UserRatingDao {
  private final UserRatingRepository ratingRepository;
  @Autowired
  public UserRatingJpaDataAccess(UserRatingRepository ratingRepository) {
    this.ratingRepository = ratingRepository;
  }


  @Override
  public Optional<UserRating> getLastUserRating(Long userId , Instant date) {
    return ratingRepository.getLastRating(userId , date);
  }

  @Override
  public void createUserRating(UserRating rating) {
     ratingRepository.save(rating);

  }

}
