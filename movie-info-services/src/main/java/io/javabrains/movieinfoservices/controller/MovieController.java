package io.javabrains.movieinfoservices.controller;

import io.javabrains.movieinfoservices.dto.Movie;
import io.javabrains.movieinfoservices.dto.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.key}")
    private String apiKey;

    @GetMapping("/{id}")
    public Movie getMovieInfo(@PathVariable final Integer id) {
        final MovieSummary movieSummary = restTemplate.getForObject(String.format("https://api.themoviedb.org/3/movie/%d?api_key=%s", id, apiKey), MovieSummary.class);
        return new Movie(id, movieSummary.getTitle(), movieSummary.getOverview());
    }
}
