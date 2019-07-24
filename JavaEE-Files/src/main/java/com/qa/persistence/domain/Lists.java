package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Lists {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long listId;
	private String listName;

	@ManyToOne
	private Account account;
	

	public Lists(long listId, String listName, long accountId) {
		super();
		this.listId = listId;
		this.listName = listName;

	}

	public Lists() {

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
		
		return account;
	}
	
	

	public void setUser(Account account) {
		this.account = account;
	}

}


