package com.qa.persistence.repository;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.exceptions.AccountNotFoundException;
import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Default
@Transactional(value = TxType.SUPPORTS)
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
		return this.json.getJSONForObject(toCreate);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String deleteAccount(Long accountID) throws AccountNotFoundException {
		this.manager.remove(this.manager.find(Account.class, accountID));
		return "SUCCESS - Account deleted.";
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String updateAccount(Long accountID, String account) throws AccountNotFoundException {
		Account newAccount = this.json.getObjectForJSON(account, Account.class);
		Account existing = this.manager.find(Account.class, accountID);
		if (existing == null) {
			throw new AccountNotFoundException();
		}
		existing.setUsername(newAccount.getUsername());
		existing.setEmail(newAccount.getEmail());
		existing.setFullName(newAccount.getFullName());
		existing.setPassword(newAccount.getPassword());
		
		this.manager.persist(existing);
		return "SUCCESS - User updated.";
	}
	
	@Override
	public String login(String account) {
		
		Account newAccount = this.json.getObjectForJSON(account, Account.class);
		String username = newAccount.getUsername();
		String password = newAccount.getPassword();
		
		String selectQuery = "SELECT ua FROM Account ua WHERE ua.username=:userName and ua.password=:passWord";

		Query query =  this.manager.createQuery(selectQuery);
		query.setParameter("userName", username);
		query.setParameter("passWord", password);
		
		Account logacc  = (Account) query.getSingleResult();
		
		return this.json.getJSONForObject(logacc);
	}
	
	@Override
	public boolean checkUsername(String account) {
		Account aUser = this.json.getObjectForJSON(account, Account.class);
		String username = aUser.getUsername();
		TypedQuery<Account> query = this.manager.createQuery("SELECT u FROM Account u WHERE username='" + username + "'",
				Account.class);
		if (query.getResultList().isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

}
