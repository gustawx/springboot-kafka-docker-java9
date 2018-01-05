package integration;

import app.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = App.class)
public class MoviesSetControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void rootPath(){
        ResponseEntity<String> response = restTemplate.getForEntity("/", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        String body = response.getBody();
        assertThat(body, containsString("releaseYear"));
        assertThat(body, containsString("title"));
        assertThat(body, containsString("actors"));
    }

    @Test
    public void moviesYears(){
        String body =  restTemplate.getForObject("/api/movies/years", String.class);
        int numberOfItems = body.split(" ").length;
        assertThat(numberOfItems, equalTo(13891));
    }
}
