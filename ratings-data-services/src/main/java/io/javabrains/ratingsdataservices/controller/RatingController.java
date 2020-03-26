package io.javabrains.ratingsdataservices.controller;

import io.javabrains.ratingsdataservices.dto.Rating;
import io.javabrains.ratingsdataservices.dto.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable final Integer movieId) {
        return new Rating(movieId, 4);
    }

    @GetMapping("users/{userId}")
    public UserRating getRatings(@PathVariable final String userId) {
        return new UserRating(asList(
                new Rating(100, 4),
                new Rating(101, 5)
        ));
    }
}
