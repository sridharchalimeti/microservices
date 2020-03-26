package io.javabrains.moviecatglogservices.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.javabrains.moviecatglogservices.dto.CatalogItem;
import io.javabrains.moviecatglogservices.dto.Movie;
import io.javabrains.moviecatglogservices.dto.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;

@Service
public class MovieInfoService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        final Movie movie = restTemplate.getForObject(format("http://movie-info-service/movies/%s", rating.getMovieId()), Movie.class);

                    /*final Movie movie = builder.build()
                            .get()
                            .uri(String.format("http://localhost:8082/movies/%s", rating.getMovieId()))
                            .retrieve()
                            .bodyToMono(Movie.class)
                            .block();*/


        return new CatalogItem(movie.getTitle(), movie.getName(), rating.getRating());
    }

    private CatalogItem getFallbackCatalogItem(Rating rating){
        return new CatalogItem("No Movie", "", 0);
    }
}
