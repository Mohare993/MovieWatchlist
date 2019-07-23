package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.qa.service.MovieListService;

@Path("/movielist")
public class MovieListController {

	@Inject
	private MovieListService service;

	@GET
	@Path("/getAll")
	public String getAllAccounts() {
		return this.service.getAllMovies();
	}

	@POST
	@Path("/createMovie")
	public String createAccount(String movie) {
		return this.service.createMovie(movie);
	}

	@DELETE
	@Path("/delete/{id}")
	public String deleteMovie(@PathParam("id") String imdbId) {
		return this.service.deleteMovie(imdbId);
	}

	
}
