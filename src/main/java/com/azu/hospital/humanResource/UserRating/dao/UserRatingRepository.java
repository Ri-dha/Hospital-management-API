package com.azu.hospital.humanResource.UserRating.dao;

import com.azu.hospital.humanResource.UserRating.entity.UserRating;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.Optional;

@Transactional
public interface UserRatingRepository extends JpaRepository<UserRating,Long> {

  @Query("SELECT r FROM UserRating r where r.user.id =:userId AND " +
          "EXTRACT(YEAR from r.createdAt) = EXTRACT(YEAR FROM CAST(:date AS TIMESTAMP ))" +
          "AND EXTRACT(MONTH from r.createdAt) = EXTRACT(MONTH FROM CAST(:date AS TIMESTAMP ))" +
          "order by r.updatedAt desc , r.createdAt LIMIT 1 ")
  Optional<UserRating> getLastRating(@Param("userId") Long userId , Instant date);

}
