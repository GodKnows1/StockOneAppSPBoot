package com.phase3.stockone.controller;

import java.util.ArrayList;
//add jwt.secret=abcd to application properties
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.phase3.stockone.*;
import com.phase3.stockone.dao.UsersAppRepository;
import com.phase3.stockone.entities.UsersApp;
@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	UsersAppRepository userrepo;

	@Autowired
	UsersAppRepository userrepo2;	
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
	       
     UsersApp user = new UsersApp() ;
     
     List<SimpleGrantedAuthority> authorities = new ArrayList<>();
      
     
         authorities.add(new SimpleGrantedAuthority(user.getRole()));
    
      
     return authorities;
 }
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsersApp user = userrepo2.findByName(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	//non dto code below	//return new org.springframework.security.core.userdetails.User(user.getname(), user.getpassword(),
			//	new ArrayList<>());
		return new Userdetails1(user);//you have to implement userdetails if you dont want to use dto
	}

//implement without dto	public com.stockexchange.phase3.User1 save(UserDto user) {
	public UsersApp save(UsersApp user) {
		UsersApp newUser = new UsersApp();
		//newUser.setname(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
	    newUser.setName(user.getName());
//	    newUser.setPassword(user.getPassword());
	    newUser.setEmail(user.getEmail());
		newUser.setRole("admin");
		newUser.setMobileNum(user.getMobileNum());
		newUser.setAdmin(user.getAdmin());
		newUser.setConfirmed(user.getConfirmed());
		return userrepo.save(newUser);
	}





	}
