package dev.sandeep.BookMyShowNov25.service;

import dev.sandeep.BookMyShowNov25.entity.Movie;
import java.util.List;

public interface MovieService {
    Movie save(Movie movie);
    Movie getById(int movieId);
    void deleteById(int movieId);
    List<Movie> getAll();
    Movie update(int movieId, Movie newMovie);
}
