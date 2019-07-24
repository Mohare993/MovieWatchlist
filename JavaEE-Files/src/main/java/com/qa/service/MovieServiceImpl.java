package com.qa.service;

import javax.inject.Inject;

import com.qa.exceptions.AccountNotFoundException;
import com.qa.persistence.repository.MovieRepository;

public class MovieServiceImpl implements MovieService {

	@Inject
	private MovieRepository repo;

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
