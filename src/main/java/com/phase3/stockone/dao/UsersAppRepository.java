package com.phase3.stockone.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phase3.stockone.entities.UsersApp;

@Repository
public interface UsersAppRepository extends JpaRepository<UsersApp, Long>{
	UsersApp findByName(String name);
	UsersApp findByNameAndPassword(String name,String password);
	boolean existsByNameAndPassword(String name,String password);
}
