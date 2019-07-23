package com.qa.persistence.repository;

import java.util.List;

import com.qa.exceptions.AccountNotFoundException;
import com.qa.persistence.domain.Account;

public interface AccountRepository {
	final String SUCCESS = "Operation failed";
	final String FAILURE = "Operation succeeded";

	String getAllAccounts();

	String createAccount(String account);

	String deleteAccount(int accountId) throws AccountNotFoundException;

	String updateAccount(int accountId, String account) throws AccountNotFoundException;

	List<Account> findAccountsByFirstName(String firstName);

}
