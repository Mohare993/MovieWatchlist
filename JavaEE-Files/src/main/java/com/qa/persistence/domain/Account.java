package com.qa.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accountId;
	@Column(length = 100)
	private String username;
	@Column(length = 100)
	private String email;
	@Column(length = 100)
	private String fullName;
	@Column(length = 100)
	private String password;

	public Account(Integer accountId, String username, String email, String fullName, String password) {
		super();
		this.accountId = accountId;
		this.username = username;
		this.email = email;
		this.fullName = fullName;
		this.password = password;
	}

	public Account() {

	}

	public Integer getId() {
		return accountId;
	}

	public void setId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
