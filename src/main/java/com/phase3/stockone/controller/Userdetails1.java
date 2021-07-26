package com.phase3.stockone.controller;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.phase3.stockone.entities.UsersApp;
//this class is required if your not using DTO and directly accessing
// the user pojo class
public class Userdetails1 implements UserDetails {
    private UsersApp user;

    public Userdetails1(UsersApp user) {
        this.user = user;
        System.out.println("withoutDTO");
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getRole() {
		return user.getRole();
	}
	public long getId() {
		return user.getId();
	}
	public String getName() {
		return user.getName();
	}
//	public String getPassword() {
//		return user.getPassword();
//	}
	public String getEmail() {
		return user.getEmail();
	}
	public Boolean getConfirmed() {
		return user.getConfirmed();
	}
	public Boolean getAdmin() {
		return user.getAdmin();
	}
	public String getMobileNum() {
		return user.getEmail();
	}
    
}

