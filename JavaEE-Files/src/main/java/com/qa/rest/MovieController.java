package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


import com.qa.service.MovieService;

@Path("/movie")
public class MovieController {

	@Inject
	private MovieService service;

	@GET
	@Path("/getAll")
	public String getAllAccounts() {
		return this.service.getAllMovies();
	}

	@POST
	@Path("/createMovie/{listId}")
	public String createAccount(@PathParam("listId")Long listId, String movie) {
		return this.service.createMovie(listId, movie);
	}

	@DELETE
	@Path("/delete/{id}")
	public String deleteMovie(@PathParam("id") Long listId) {
		return this.service.deleteMovie(listId);
	}
	
	@GET
	@Path("/get/{listId}")
	public String getAllMoviesForList(@PathParam("listId") Long listId) {
		return this.service.getAllMoviesForList(listId);
}

	
}
