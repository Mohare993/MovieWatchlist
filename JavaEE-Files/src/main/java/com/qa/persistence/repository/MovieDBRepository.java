package com.qa.persistence.repository;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.exceptions.AccountNotFoundException;
import com.qa.persistence.domain.Movie;
import com.qa.util.JSONUtil;

@Default
@Transactional(value = TxType.SUPPORTS)
public class MovieDBRepository implements MovieRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil json;

	@Override
	public String getAllMovies() {
		TypedQuery<Movie> query = this.manager.createQuery("SELECT m from MovieList m", Movie.class);
		return this.json.getJSONForObject(query.getResultList());
	}
	
	@Override
	@Transactional(value = TxType.REQUIRED)
	public String createMovie(String movie) {
		Movie toCreate = this.json.getObjectForJSON(movie, Movie.class);
		this.manager.persist(toCreate);
		return "SUCCESS - Movie created.";
	}
	
	@Override
	@Transactional(value = TxType.REQUIRED)
	public String deleteMovie(String imdbId) throws AccountNotFoundException {
		this.manager.remove(this.manager.find(Movie.class, imdbId));
		return "SUCCESS - Movie deleted.";
	}
	
	


}

