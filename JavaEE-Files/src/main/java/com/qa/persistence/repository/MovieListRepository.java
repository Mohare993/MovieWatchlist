package com.qa.persistence.repository;

import com.qa.exceptions.AccountNotFoundException;

public interface MovieListRepository{

		String getAllMovies();

		String createMovie(String movie);

		String deleteMovie(String imdbId) throws AccountNotFoundException;


}
