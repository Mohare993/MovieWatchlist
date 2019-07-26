package com.qa.service;

import com.qa.exceptions.AccountNotFoundException;

public interface MovieService {
	
	String getAllMovies();

	String createMovie(Long listId, String movie);

	String deleteMovie(Long listId) throws AccountNotFoundException;
	
	String getAllMoviesForList(Long listId);


}
