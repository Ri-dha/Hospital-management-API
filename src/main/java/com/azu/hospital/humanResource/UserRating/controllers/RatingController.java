package com.azu.hospital.humanResource.UserRating.controllers;

import com.azu.hospital.Validator.DateValidator.DatePattern;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.dto.BaseUserDto;
import com.azu.hospital.humanResource.UserRating.dto.UserRatingDto;
import com.azu.hospital.humanResource.UserRating.request.UserRatingRequest;
import com.azu.hospital.humanResource.UserRating.services.UserRatingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/human-resource/user-rating")
public class RatingController {
  private final UserRatingService ratingService;
  @Autowired
  public RatingController(
          @Qualifier("userRatingService") UserRatingService ratingService) {
    this.ratingService = ratingService;
  }
  @PostMapping("/add")
  public ResponseEntity<Void> createRating(
          @RequestParam("userId") Long userId,
          @Valid @RequestBody UserRatingRequest request){
             ratingService.createUserRating(userId,request);
             return ResponseEntity.ok().build();
  }

  @GetMapping("/last-rating")
  public ResponseEntity<UserRatingDto> getLastRating(
          @RequestParam(name = "userId") Long userId)
          {
            return ResponseEntity.ok(ratingService.getLastRating(userId));
  }

  @GetMapping("/user/all")
  public ResponseEntity<Page<BaseUserDto>> getRatingByUserId(
    @RequestParam @DatePattern String date,
    @PageableDefault(page = 0 ,size = 15) Pageable pageable)
  {
    return ResponseEntity.ok(ratingService.getRatingByDate(date,pageable));
  }

}
