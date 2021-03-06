package com.qa.persistence.repository;

import com.qa.exceptions.AccountNotFoundException;

public interface MovieRepository{

		String getAllMovies();

		String createMovie(Long listId, String movie);

		String deleteMovie(Long movieId) throws AccountNotFoundException;
		
		String getAllMoviesForList(Long listId);


}
