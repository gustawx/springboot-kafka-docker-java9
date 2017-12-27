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
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@RestController
public class MoviesController {
    Log log = LogFactory.getLog(MoviesController.class);
//    @RequestMapping(value = "/movies/all",
//            method= RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public Map<String, String> movies(){
//        Map<String, String> m = new HashMap<String, String>();
//        m.put("FILMY", "jakies filmy");
//        return m;
//    }
    /*
    @RequestMapping(value = "/movies/stream",
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public StreamingResponseBody streamMovies() {
        return new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                for (int i = 0; i < 1000; i++) {
                    outputStream.write((Integer.toString(i) + " - ")
                            .getBytes());
                    outputStream.flush();
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }*/

    @RequestMapping(value = "/movies/stream",
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public StreamingResponseBody streamMovies() {
        return new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) {
                MovieParser.getMovies()
                        .forEach(el -> {
                            log.info(el.title() + ": " + el.releaseYear() + "\n");
                            try {
                                outputStream.write((el.toString() + " ").getBytes());
                                outputStream.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                log.error("Interrupted exception, probably by the client");
                            }
                        });
            }

        };
    }
}
