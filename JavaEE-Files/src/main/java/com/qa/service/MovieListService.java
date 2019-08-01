package com.qa.service;

import javax.inject.Inject;

import com.qa.exceptions.AccountNotFoundException;
import com.qa.persistence.repository.MovieListRepository;

public class  MovieListService {

	@Inject
	private MovieListRepository repo;

	public String CreateList(Long userId,String lists) {

		return this.repo.CreateList(userId,lists);
	}

	public String getAllLists() {
		return this.repo.getAllLists();
	}

	public String updateList(Long listId, String lists) {

		return this.repo.updateList(listId, lists);

	}

	public String deleteList(Long listId) throws AccountNotFoundException {
		return this.repo.deleteList(listId);
	}
	
	public String getAllListsForAcc(Long userId) {
		return this.repo.getAllListsForAcc(userId);
	}
}
