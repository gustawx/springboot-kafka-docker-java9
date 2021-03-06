package app.controller;

import app.service.MovieParser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("api/movies/")
public class MoviesSetController {
    // streams all movies from Set<Movie> of all movies
    @RequestMapping(value = "years", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public StreamingResponseBody streamMovies() {
        return new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) {
                MovieParser.getMovies()
                        .forEach(el -> {
                            try {
                                outputStream.write((el.releaseYear() + " ").getBytes());
                                outputStream.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
            }

        };
    }
}
