package unit;

import app.App;
import app.controller.HomeController;
import app.model.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = App.class)
public class MoviesControllerTest {

    @MockBean
    private HomeController homeController;

    @Test
    public void getMoviesTest(){
        given(this.homeController.home()).willReturn(Set.of(
                    new Movie("Fake title", 1982),
                    new Movie("Another Fake title", 2007))
                );

        Set<Movie> m = homeController.home();
        Set<Movie> mockM = (Set.of(
                new Movie("Fake title", 1982),
                new Movie("Another Fake title", 2007)));

        System.out.println(mockM.equals(m));
        assertThat(m, equalTo(mockM));
    }
}
