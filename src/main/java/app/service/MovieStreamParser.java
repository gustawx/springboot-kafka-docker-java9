package app.service;

import app.model.Movie;
import app.utils.FileUtil;

import java.io.IOException;
import java.util.function.Function;
import java.util.stream.Stream;

public class MovieStreamParser {
    private Function<String, Movie> lineToMovie =
                        line -> {
                            String[] elements = line.split("/");
                            String title =
                                    elements[0].substring(0, elements[0].lastIndexOf("(")).trim();
                            String releaseYear =
                                    elements[0].substring(elements[0].lastIndexOf("(") + 1, elements[0].lastIndexOf(")"));

                            // in some cases there are too many comas
                            if (releaseYear.contains(",")) {
                                releaseYear = releaseYear.split(",")[0];
                            }

                            return new Movie(title, Integer.valueOf(releaseYear));
                        };

    public Stream<Movie> getMovies() throws IOException {
        return FileUtil.getLines("/movies/movies-mpaa.txt")
                .map(lineToMovie);
    }
}
