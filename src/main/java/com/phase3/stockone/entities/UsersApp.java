package com.phase3.stockone.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UsersApp")//don’t’ use user as table name as it is reserved word in some dbs)
public class UsersApp {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String password;
	private String email;
	private Boolean confirmed;
	private Boolean admin;
	private String mobileNum;
	
	//
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public UsersApp(String name, String password, String email, Boolean confirmed, Boolean admin, String mobileNum,String role) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.confirmed = confirmed;
		this.admin = admin;
		this.mobileNum = mobileNum;
		this.role=role;
	}
	public UsersApp() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}