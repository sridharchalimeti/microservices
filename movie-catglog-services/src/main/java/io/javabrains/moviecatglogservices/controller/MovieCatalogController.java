package io.javabrains.moviecatglogservices.controller;

import io.javabrains.moviecatglogservices.dto.CatalogItem;
import io.javabrains.moviecatglogservices.dto.UserRating;
import io.javabrains.moviecatglogservices.service.MovieInfoService;
import io.javabrains.moviecatglogservices.service.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder builder;

    @Autowired
    private MovieInfoService movieInfoService;

    @Autowired
    private UserRatingService userRatingService;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalogItems(@PathVariable final String userId) {

        // Get all movie Ids
        final UserRating userRating = userRatingService.getUserRatings(userId);

        return userRating.getRatings().stream()
                .map(rating -> movieInfoService.getCatalogItem(rating))
                .collect(Collectors.toList());
    }

}
