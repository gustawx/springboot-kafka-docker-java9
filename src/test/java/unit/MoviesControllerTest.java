package unit;

import app.App;
import app.controller.HomeController;
import app.model.Movie;
import app.service.MovieParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = App.class)
public class MoviesControllerTest {

    @PrepareForTest(MovieParser.class)
    @Test
    public void getMoviesTest(){
        mockStatic(MovieParser.class);
        PowerMockito.when(MovieParser.getMovies()).thenReturn(Set.of(
                new Movie("Fake title", 1982),
                new Movie("Another Fake title", 2007)));

        HomeController hc = new HomeController();

        Set<Movie> m = hc.home();
        Set<Movie> mockM = (Set.of(
                new Movie("Fake title", 1982),
                new Movie("Another Fake title", 2007)));

        assertThat(m, equalTo(mockM));

        assertThat(m, hasItems(new Movie("Fake title", 1982),
                new Movie("Another Fake title", 2007)));
    }
}
