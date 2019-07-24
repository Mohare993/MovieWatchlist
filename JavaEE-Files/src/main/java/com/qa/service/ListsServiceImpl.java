package com.qa.service;

import javax.inject.Inject;

import com.qa.persistence.repository.ListsDBRepository;
import com.qa.persistence.repository.ListsRepository;

public class ListsServiceImpl implements ListsRepository {
	
	@Inject
	private ListsDBRepository repo;

	public String CreateList(Integer accountId,String listsTodo) {

		return this.repo.CreateList(accountId,listsTodo);
	}

	public String getAllLists() {
		return this.repo.getAllLists();
	}

	public String updateList(long listId, String listsTodo) {

		return this.repo.updateList(listId, listsTodo);

	}

	public String deleteList(long listId) {
		return this.repo.deleteList(listId);
	}

}

