package com.qa.persistence.repository;

import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.exceptions.AccountNotFoundException;
import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(value = TxType.SUPPORTS)
@Default
public class AccountDBRepository implements AccountRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil json;

	@Override
	public String getAllAccounts() {
		TypedQuery<Account> query = this.manager.createQuery("SELECT a from Account a", Account.class);
		return this.json.getJSONForObject(query.getResultList());
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String createAccount(String account) {
		Account toCreate = this.json.getObjectForJSON(account, Account.class);
		this.manager.persist(toCreate);
		return SUCCESS;
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String deleteAccount(int accountNumber) throws AccountNotFoundException {
		this.manager.remove(this.manager.find(Account.class, accountNumber));
		return SUCCESS;
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String updateAccount(int accountID, String account) throws AccountNotFoundException {
		Account newAccount = this.json.getObjectForJSON(account, Account.class);
		Account existing = this.manager.find(Account.class, accountID);
		if (existing == null) {
			throw new AccountNotFoundException();
		}
		existing.setAccountNumber(newAccount.getAccountNumber());
		existing.setFirstName(newAccount.getFirstName());
		existing.setLastName(newAccount.getLastName());
		this.manager.persist(existing);
		return SUCCESS;
	}

	@Override
	public List<Account> findAccountsByFirstName(String firstName) {
		TypedQuery<Account> query = this.manager.createQuery("SELECT a FROM Account a WHERE a.firstName = :firstName",
				Account.class);
		query.setParameter("firstName", firstName);
		return query.getResultList();
	}

}
