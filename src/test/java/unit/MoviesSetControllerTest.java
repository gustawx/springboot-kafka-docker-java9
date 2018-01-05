package unit;

import app.App;
import app.controller.HomeController;
import app.model.Movie;
import app.service.MovieParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = App.class)
public class MoviesSetControllerTest {

    @InjectMocks
    private HomeController homeController;

    @PrepareForTest(MovieParser.class)
    @Test
    public void getMoviesTest(){
        mockStatic(MovieParser.class);
        Movie movie1 = new Movie("Fake title", 1982);
        Movie movie2 = new Movie("Another Fake title", 2007);
        PowerMockito.when(MovieParser.getMovies())
                    .thenReturn(Set.of(movie1, movie2));

        Set<Movie> callAllMovies = homeController.home();
        assertThat(callAllMovies, hasItems(movie1, movie2));
    }
}
