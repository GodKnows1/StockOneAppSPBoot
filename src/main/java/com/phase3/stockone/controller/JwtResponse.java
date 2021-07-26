package com.phase3.stockone.controller;

import java.io.Serializable;

import com.phase3.stockone.entities.UsersApp;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final UsersApp usersApp;

	public JwtResponse(String jwttoken,UsersApp user) {
		this.jwttoken = jwttoken;
		this.usersApp =user;
	}

	public UsersApp getUsersApp() {
		return usersApp;
	}

	public String getToken() {
		return this.jwttoken;
	}
}
