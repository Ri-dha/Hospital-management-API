package com.azu.hospital.humanResource.UserRating.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UserRatingRequest(

        @NotNull(message = "Rating should not be null")
        @Min(value = 1,message = "Rating should be minimum 1")
        @Max(value = 5,message = "Rating should be maximum 5")
        Integer rating
){
}
