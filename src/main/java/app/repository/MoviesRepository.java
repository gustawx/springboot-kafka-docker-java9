package app.repository;

import app.model.MovieBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepository extends JpaRepository<MovieBean, Long>{

}
