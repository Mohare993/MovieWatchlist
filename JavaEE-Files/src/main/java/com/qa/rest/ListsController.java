package com.qa.rest;

import javax.inject.Inject;
import javax.websocket.server.PathParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.qa.service.ListsServiceImpl;

@Path("/lists")
public class ListsController {
	
	@Inject
	private ListsServiceImpl serv;
	
	@POST
	@Path("/create/{accountId}")
	public String CreateList(@PathParam("accountId") Integer accountId,String listsTodo) {
		return this.serv.CreateList(accountId,listsTodo);
	}
	
	@GET
	@Path("/getAll")
	public String getAllLists() {
		return this.serv.getAllLists();
	}
	
	@POST
	@Path("/update/{listId}")
	public String updateList(@PathParam("listId") long listId, String listsTodo) {
		return this.serv.updateList(listId, listsTodo);
	}
	
	@DELETE
	@Path("/delete/{listId}")
	public String deleteList(@PathParam("listId") long listId) {
		return this.serv.deleteList(listId);
	}

}