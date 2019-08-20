package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.qa.exceptions.AccountNotFoundException;
import com.qa.service.AccountService;

@Path("/account")
public class AccountController {

	@Inject
	private AccountService service;

	@GET
	@Path("/getAll")
	public String getAllAccounts() {
		return this.service.getAllAccounts();
	}

	@POST
	@Path("/createAccount")
	public Response createAccount(String account) {
		try {
			return Response.ok(this.service.createAccount(account)).build();
		} catch (AccountNotFoundException anfe) {
			return Response.status(Status.CONFLICT).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("/delete/{id}")
	public String deleteAccount(@PathParam("id") Long accountId) {
		return this.service.deleteAccount(accountId);
	}

	@POST
	@Path("/update/{id}")
	public Response updateAccount(@PathParam("id") Long accountId, String account) {
	try {
		return Response.ok(this.service.updateAccount(accountId, account)).build();
	} catch (AccountNotFoundException anfe) {
		return Response.status(Status.CONFLICT).build();
	} catch (Exception e) {
		return Response.status(Status.BAD_REQUEST).build();
	}
}

	@POST
	@Path("/login")
	public Response login(String account) {
	try {
		return Response.ok(this.service.login(account)).build();
	} catch (AccountNotFoundException anfe) {
		return Response.status(Status.CONFLICT).build();
	} catch (Exception e) {
		return Response.status(Status.BAD_REQUEST).build();
	}
}

	@GET
	@Path("/get/{id}")
	public String getDetailsForAcc(@PathParam("id") Long accountId) {
		return this.service.getDetailsForAcc(accountId);
	}
}
