package com.example.demo.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import com.example.demo.Database.Entity.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    @Autowired
    private JdbcTemplate template;

    // Create Emtpy Table
    @PostConstruct
    public void createTable() {
        template.execute(
                "CREATE TABLE movies (id bigint auto_increment primary key, name VARCHAR(50), year int, rating int)");
    }

    // Query #1 INSERT
    public void createMovie(String name, int year, int rating) {
        template.update("INSERT INTO movies (name, year, rating) VALUES (?,?,?)", name, year, rating);
    }

    // Query #2 SEARCH
    public List<Movie> findMoviesByName(String likeName) {
        List<Movie> movie = template.query("SELECT m.name, m.year, m.rating FROM movies m WHERE m.name LIKE (?) ",
                new Object[] { likeName }, new MovieRowMapper());
        return movie;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext();
        config.register(Config.class);
        config.refresh();
        MovieRepository repository = config.getBean(MovieRepository.class);

        repository.createMovie("Some movie", 1974, 3);
        repository.createMovie("Some other movie", 1993, 2);

        List<Movie> movies = repository.findMoviesByName("Some%");
        for (Movie movie : movies) {
            System.out.println(movie.name + " - " + movie.year + " - " + movie.rating);
        }
    }
}

/**
 * Use by Query #2, JDBC to map result into JAVA object
 */
class MovieRowMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {

        Movie movie = new Movie(rs.getString(1), rs.getInt(2), rs.getInt(3));

        return movie;
    }
}

// Configuration of H2
@Configuration
@Import(MovieRepository.class)
class Config {
    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DriverManagerDataSource ds) {
        return new JdbcTemplate(ds);
    }
}

