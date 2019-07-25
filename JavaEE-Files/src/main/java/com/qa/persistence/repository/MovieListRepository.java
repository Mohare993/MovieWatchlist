package com.qa.persistence.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.persistence.domain.Account;
import com.qa.persistence.domain.MovieList;
import com.qa.util.JSONUtil;

@Transactional(value = TxType.REQUIRED)
public class MovieListRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil gson;
	
	

	public String CreateList(Long userId,String listsTodo) {
		Account listOwner = this.manager.find(Account.class, userId);
		MovieList newList = this.gson.getObjectForJSON(listsTodo, MovieList.class);
		newList.setUser(listOwner);
		this.manager.persist(newList);
		
		return "Success for:" + this.gson.getObjectForJSON(listsTodo,  MovieList.class).getListName();
	}

	@Transactional(value = TxType.SUPPORTS)
	public String getAllLists() {
		TypedQuery<MovieList> query = this.manager.createQuery("SELECT l FROM ListsTodo l",  MovieList.class);
		return this.gson.getJSONForObject(query.getResultList());
	}
	
	
	public String updateList(long listId, String listsTodo) {
		 MovieList current = this.manager.find( MovieList.class, listId);
		 MovieList newList = this.gson.getObjectForJSON(listsTodo, MovieList.class);
		current.setListName(newList.getListName());
		
		this.manager.persist(current);
		return "Success for: "+ current.getListName();
	}
	
	public String deleteList(long listId) {
		this.manager.remove(this.manager.find(MovieList.class, listId));
		return "Deleted List";
	}

}
