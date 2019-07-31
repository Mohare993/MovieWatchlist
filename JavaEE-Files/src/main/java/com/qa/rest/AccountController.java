package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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
	public String createAccount(String account) {
		return this.service.createAccount(account);
	}

	@DELETE
	@Path("/delete/{id}")
	public String deleteAccount(@PathParam("id") Long accountId) {
		return this.service.deleteAccount(accountId);
	}

	@POST
	@Path("/update/{id}")
	public String updateAccount(@PathParam("id") Long accountId, String account) {
		return this.service.updateAccount(accountId, account);
	}
	
	@POST
	@Path("/login")
	public String login(String account) {
		return this.service.login(account);
	}
	
	@GET
	@Path("/get/{id}")
	public String getDetailsForAcc(@PathParam("id") Long accountId) {
		return this.service.getDetailsForAcc(accountId);
	}
}
