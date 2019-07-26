package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.qa.service. MovieListService;

@Path("/lists")
public class MovieListController {
	
	@Inject
	private MovieListService serv;
	
	@POST
	@Path("/create/{userId}")
	public String CreateList(@PathParam("userId") Long userId,String lists) {
		return this.serv.CreateList(userId,lists);
	}
	
	@GET
	@Path("/getAll")
	public String getAllLists() {
		return this.serv.getAllLists();
	}
	
	@POST
	@Path("/update/{listId}")
	public String updateList(@PathParam("listId") Long listId, String lists) {
		return this.serv.updateList(listId, lists);
	}
	
	@DELETE
	@Path("/delete/{listId}")
	public String deleteList(@PathParam("listId") Long listId) {
		return this.serv.deleteList(listId);
	}
	
	@GET
	@Path("/get/{userId}")
	public String getAllListsForAcc(@PathParam("userId") Long userId) {
		return this.serv.getAllListsForAcc(userId);
}}

