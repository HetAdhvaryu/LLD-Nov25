package dev.sandeep.BookMyShowNov25.service;

import dev.sandeep.BookMyShowNov25.entity.Movie;
import dev.sandeep.BookMyShowNov25.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepo movieRepo;

    @Override
    public Movie save(Movie movie) {
        return movieRepo.save(movie);
    }

    @Override
    public Movie getById(int movieId) {
        return movieRepo.findById(movieId).orElse(null);
    }

    @Override
    public void deleteById(int movieId) {
        movieRepo.deleteById(movieId);
    }

    @Override
    public List<Movie> getAll() {
        return movieRepo.findAll();
    }

    @Override
    public Movie update(int movieId, Movie newMovie) {
        if (movieRepo.existsById(movieId)) {
            newMovie.setId(movieId);
            return movieRepo.save(newMovie);
        }
        return null;
    }
}
