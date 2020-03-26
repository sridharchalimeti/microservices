package io.javabrains.moviecatglogservices.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.javabrains.moviecatglogservices.dto.Rating;
import io.javabrains.moviecatglogservices.dto.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static java.lang.String.format;

@Service
public class UserRatingService {

    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "getFallbackUserRatings")
    public UserRating getUserRatings(String userId) {
        return restTemplate.getForObject(format("http://RATINGS-DATA-SERVICE/ratings/users/%s", userId), UserRating.class);
    }

    private UserRating getFallbackUserRatings(String userId) {
        final UserRating userRating = new UserRating();
        userRating.setRatings(Arrays.asList(new Rating(0, 0)));
        return userRating;
    }
}
