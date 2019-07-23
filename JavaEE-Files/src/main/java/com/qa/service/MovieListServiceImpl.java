package com.qa.service;

import javax.inject.Inject;

import com.qa.exceptions.AccountNotFoundException;
import com.qa.persistence.repository.MovieListRepository;

public class MovieListServiceImpl implements MovieListService {

	@Inject
	private MovieListRepository repo;

	@Override
	public String getAllMovies() {
		return this.repo.getAllMovies();
	}

	@Override
	public String createMovie(String movie) {
		return this.repo.createMovie(movie);
	}

	@Override
	public String deleteMovie(String imbdId) throws AccountNotFoundException {
		return this.repo.deleteMovie(imbdId);
	}

}
