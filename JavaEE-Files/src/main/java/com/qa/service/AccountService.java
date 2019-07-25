package com.qa.service;

import com.qa.exceptions.AccountNotFoundException;


public interface AccountService {
	
	String getAllAccounts();

	String createAccount(String account);

	String deleteAccount(Long accountId) throws AccountNotFoundException;

	String updateAccount(Long accountId, String account) throws AccountNotFoundException;
	
	String login(String account);

}
