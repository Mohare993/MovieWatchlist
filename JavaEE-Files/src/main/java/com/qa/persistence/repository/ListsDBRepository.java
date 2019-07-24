package com.qa.persistence.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.persistence.domain.Account;
import com.qa.persistence.domain.Lists;
import com.qa.util.JSONUtil;

@Transactional(value = TxType.REQUIRED)
public class ListsDBRepository implements ListsRepository{

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil gson;

	public String CreateList(Integer accountId, String lists) {
		Account listOwner = this.manager.find(Account.class, accountId);
		Lists newList = this.gson.getJSONForObject(lists, Lists.class);
		newList.setUser(listOwner);
		this.manager.persist(newList);

		return "Success for:" + this.gson.getObjectForJSON(lists, Lists.class).getListName();
	}

	@Transactional(value = TxType.SUPPORTS)
	public String getAllLists() {
		TypedQuery<Lists> query = this.manager.createQuery("SELECT l FROM Lists l", Lists.class);
		return this.gson.getJSONForObject(query.getResultList());
	}

	public String updateList(long listId, String lists) {
		Lists current = this.manager.find(Lists.class, listId);
		Lists newList = this.gson.getObjectForJSON(lists, Lists.class);
		current.setListName(newList.getListName());

		this.manager.persist(current);
		return "Success for: " + current.getListName();
	}

	public String deleteList(long listId) {
		this.manager.remove(this.manager.find(Lists.class, listId));
		return "Deleted List";
	}

	

}
