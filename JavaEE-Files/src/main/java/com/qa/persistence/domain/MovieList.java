package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MovieList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long listId;
	private String listName;

	@ManyToOne
	private Account user;
	

	public MovieList(long listId, String listName, Long userId) {
		super();
		this.listId = listId;
		this.listName = listName;

	}

	public MovieList() {

	}

	public long getListId() {
		return listId;
	}

	public void setListId(long listId) {
		this.listId = listId;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public Account getUser() {
		
		return user;
	}
	
	

	public void setUser(Account user) {
		this.user = user;
	}

}
