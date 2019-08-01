package com.qa.persistence.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.exceptions.AccountNotFoundException;
import com.qa.persistence.domain.Account;
import com.qa.persistence.domain.MovieList;
import com.qa.util.JSONUtil;

@Transactional(value = TxType.REQUIRED)
public class MovieListRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil gson;
	
	
	
	@Transactional(value = TxType.REQUIRED)
	public String CreateList(Long userId,String lists) {
		Account listOwner = this.manager.find(Account.class, userId);
		MovieList newList = this.gson.getObjectForJSON(lists, MovieList.class);
		newList.setUser(listOwner);
		this.manager.persist(newList);
		
		return "Success for:" + this.gson.getObjectForJSON(lists,  MovieList.class).getListName();
	}

	public String getAllLists() {
		TypedQuery<MovieList> query = this.manager.createQuery("SELECT l FROM MovieList l",  MovieList.class);
		return this.gson.getJSONForObject(query.getResultList());
	}
	
	public String getAllListsForAcc(Long userId) {
		TypedQuery<MovieList> query = this.manager.createQuery("SELECT l FROM MovieList l WHERE USER_ACCOUNTID='" + userId + "'",  MovieList.class);
		return this.gson.getJSONForObject(query.getResultList());
	}
	
	@Transactional(value = TxType.REQUIRED)
	public String updateList(Long listId, String list) {
		 MovieList current = this.manager.find( MovieList.class, listId);
		 MovieList newList = this.gson.getObjectForJSON(list, MovieList.class);
		current.setListName(newList.getListName());
		
		this.manager.persist(current);
		return "Success for: "+ current.getListName();
	}
	
	@Transactional(value = TxType.REQUIRED)
	public String deleteList(Long listId) throws AccountNotFoundException {
		this.manager.remove(this.manager.find(MovieList.class, listId));
		return "Deleted List";
	}


}
