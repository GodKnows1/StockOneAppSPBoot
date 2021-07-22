package com.phase3.stockone.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phase3.stockone.entities.StockExchange;

@Repository
public interface StockExchangeRepository extends JpaRepository<StockExchange, Long>{
	public StockExchange findByName(String name);
	
	@Query("Select c.companyName FROM Company c JOIN c.compstockmap p JOIN p.stockexchange q WHERE q.name=:name")
	public List<String> findCompByExchange(@Param("name")String name);
}
