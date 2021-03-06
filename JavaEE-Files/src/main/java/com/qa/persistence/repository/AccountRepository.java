package com.qa.persistence.repository;

import com.qa.exceptions.AccountNotFoundException;



public interface AccountRepository {
	
	final String SUCCESS = "Operation failed";
	final String FAILURE = "Operation succeeded";

	String getAllAccounts();

	String createAccount(String account);

	String deleteAccount(Long accountId) throws AccountNotFoundException;

	String updateAccount(Long accountId, String account) throws AccountNotFoundException;
	
	String login(String account);
	
	boolean checkUsername(String account);

	String getDetailsForAcc(Long accountId) throws AccountNotFoundException;


}
