package com.revshop.rev.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
@NamedQueries({
	@NamedQuery(name = "LoginAdmin", 
			  query = "FROM Admin WHERE email = :email AND password = :password")
})

public class Admin {

	@Id
	@Column(unique = true, nullable = false)
	private String email;

	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
