package integration;

import app.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = App.class)
public class MoviesSetControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void rootPath(){
        String body =  restTemplate.getForObject("/", String.class);
        assertThat(body, notNullValue());
    }

    @Test
    public void moviesAll(){
        String body =  restTemplate.getForObject("/movies/all", String.class);
        assertThat(body, containsString("releaseYear"));
    }
}
