package com.qa.service;

import com.qa.exceptions.AccountNotFoundException;

public interface MovieService {
	
	String getAllMovies();

	String createMovie(String movie);

	String deleteMovie(String imdbId) throws AccountNotFoundException;

}
