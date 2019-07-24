package com.qa.service;

import com.qa.exceptions.AccountNotFoundException;
import com.qa.persistence.domain.Account;

public interface AccountService {
	
	String getAllAccounts();

	String createAccount(String account);

	String deleteAccount(int accountId) throws AccountNotFoundException;

	String updateAccount(int accountId, String account) throws AccountNotFoundException;
	
	String login(String account);
	
	
	

}
