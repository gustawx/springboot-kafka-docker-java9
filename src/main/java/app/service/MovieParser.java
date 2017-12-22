package app.service;

import app.model.Actor;
import app.model.Movie;
import app.utils.FileUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

public class MovieParser {

    public static Set<Movie> getMovies(){
        Log log = LogFactory.getLog(MovieParser.class);
        Set<Movie> movies = new HashSet<>();

        Stream<String> lines = null;
        try {
             lines = Files.lines(
                 FileUtil.getFilePath("/movies/movies-mpaa.txt")
             );
        } catch (IOException e) {
            log.error("Movies file was not found !!!");
        }

        lines.forEach(
                (String line) -> {
                    String[] elements = line.split("/");
                    String title =
                            elements[0].substring(0, elements[0].lastIndexOf("(")).trim();
                    String releaseYear =
                            elements[0].substring(elements[0].lastIndexOf("(") + 1, elements[0].lastIndexOf(")"));

                    if (releaseYear.contains(",")) {
                        // with skip movies with a coma in their title
                        return;
                    }

                    Movie movie = new Movie(title, Integer.valueOf(releaseYear));

                    for (int i = 1; i < elements.length; i++) {
                        String[] name = elements[i].split(", ");
                        String lastName = name[0].trim();
                        String firstName = "";
                        if (name.length > 1) {
                            firstName = name[1].trim();
                        }

                        Actor actor = new Actor(lastName, firstName);
                        movie.addActor(actor);
                    }
                    movies.add(movie);
                }
        );
        return movies;
    }
}
