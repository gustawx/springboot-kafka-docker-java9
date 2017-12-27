package app.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.HashSet;
import java.util.Set;

@JsonAutoDetect
public class Movie {
    @JsonSerialize
    private String title ;
    @JsonSerialize
    private int releaseYear ;
    @JsonSerialize
    private Set<Actor> actors = new HashSet<>() ;

    public Movie(String title, int releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public String title() {
        return this.title ;
    }

    public int releaseYear() {
        return this.releaseYear ;
    }

    public void addActor(Actor actor) {
        this.actors.add(actor) ;
    }

    public Set<Actor> actors() {
        return this.actors ;
    }

    @Override
    public String toString() {
        return "Movie{" + "title=" + title + ", releaseYear=" + releaseYear;// + ", actors=" + actors + '}';
    }
}