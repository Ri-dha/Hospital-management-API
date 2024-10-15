package com.azu.hospital.humanResource.UserRating.dto;

import java.time.Instant;

public record UserRatingDto(
        Long userId,
        Long ratingId,
        Integer rating,
        Instant createdAt
){
}
