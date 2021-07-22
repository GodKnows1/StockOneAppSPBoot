package com.phase3.stockone.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phase3.stockone.entities.CompanyStockExchange;

@ResponseBody
public interface CompanyStockExchangeRepository extends JpaRepository<CompanyStockExchange, Long>{

}
