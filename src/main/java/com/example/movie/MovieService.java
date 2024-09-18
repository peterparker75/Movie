package com.example.movie;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.example.movie.Movie;
import com.example.movie.MovieRepository;

import java.util.*;

public class MovieService implements MovieRepository {

    private static HashMap<Integer, Movie> movieList = new HashMap<>();
    int num = 6;

    public MovieService() {
        movieList.put(1, new Movie(1, "Avengers: Endgame", "Robert Downey Jr."));
        movieList.put(2, new Movie(2, "Avatar", "Sam Worthington"));
        movieList.put(3, new Movie(3, "Titanic", "Leonardo DiCaprio"));
        movieList.put(4, new Movie(4, "Star Wars: The Force Awakens", "Daisy Ridley"));
        movieList.put(5, new Movie(5, "Jurassic World", "Chris Pratt"));
    }

    @Override
    public ArrayList<Movie> getMovies() {
        Collection<Movie> movieCollect = movieList.values();
        ArrayList<Movie> movies = new ArrayList<>(movieCollect);
        return movies;
    }

    @Override
    public Movie addMovie(Movie movie) {
        movie.setMovieId(num);
        movieList.put(num, movie);
        num += 1;
        return movie;
    }

    @Override
    public Movie getMovieById(int movieId) {
        Movie movie = movieList.get(movieId);
        if (movie == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return movie;
    }

    @Override
    public Movie updateMovie(int movieId, Movie movie) {
        Movie oldMovies = movieList.get(movieId);
        if (oldMovies == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (movie.getMovieName() != null) {
            oldMovies.setMovieName(movie.getMovieName());
        }
        if (movie.getLeadActor() != null) {
            oldMovies.setLeadActor(movie.getLeadActor());
        }

        return oldMovies;
    }

    @Override
    public void deleteMovie(int movieId) {
        Movie newMovie = movieList.get(movieId);
        if (newMovie == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            movieList.remove(movieId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }
}
