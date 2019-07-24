package com.qa.persistence.repository;


public interface ListsRepository {
	
	public String CreateList(Integer accountId,String listsTodo);
	
	public String getAllLists();
	
	
	public String updateList(long listId, String listsTodo);
	
	
	public String deleteList(long listId);
	

}


