package com.qa.service;

import java.util.List;

import javax.inject.Inject;

import com.qa.exceptions.AccountNotFoundException;
import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {

	@Inject
	private AccountRepository repo;

	@Override
	public String getAllAccounts() {
		return this.repo.getAllAccounts();
	}

	@Override
	public String createAccount(String account) {
		return this.repo.createAccount(account);
	}

	@Override
	public String deleteAccount(int accountId) throws AccountNotFoundException {
		return this.repo.deleteAccount(accountId);
	}

	@Override
	public String updateAccount(int accountId, String account) throws AccountNotFoundException {
		return this.repo.updateAccount(accountId, account);
	}

	@Override
	public List<Account> findAccountsByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

}
