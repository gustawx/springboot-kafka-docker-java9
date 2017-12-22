package app.controller;

import app.model.Movie;
import app.service.MovieParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class HomeController {
    @RequestMapping
    public Set<Movie> home(){
        return MovieParser
                .getMovies()
                .stream()
                .limit(3)
                .collect(Collectors.toSet());
    }

}
