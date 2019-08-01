package com.qa.service;


import javax.inject.Inject;

import com.qa.exceptions.AccountNotFoundException;
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
		if (this.repo.checkUsername(account)== true) {
			return this.repo.createAccount(account);

		} else {
			throw new AccountNotFoundException();
		}
	}
	
	@Override
	public String deleteAccount(Long accountId) throws AccountNotFoundException {
		return this.repo.deleteAccount(accountId);
	}

	@Override
	public String updateAccount(Long accountId, String account) throws AccountNotFoundException {
		if (this.repo.checkUsername(account)== true) {
		return this.repo.updateAccount(accountId, account);
	}else {
		throw new AccountNotFoundException();
	}}
	
	@Override
	public String login(String account) {
	return this.repo.login(account);
	}
	
	@Override
	public String getDetailsForAcc(Long accountId) throws AccountNotFoundException {
		return this.repo.getDetailsForAcc(accountId);
	}



}
