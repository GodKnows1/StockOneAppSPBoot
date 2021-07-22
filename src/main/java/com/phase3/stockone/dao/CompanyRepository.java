package com.phase3.stockone.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phase3.stockone.entities.Company;
import com.phase3.stockone.entities.StockExchange;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
	public Company findBycompanyName(String companyName);
	
	@Query("SELECT m.companyName FROM Company m WHERE m.companyName LIKE %:companyName%")
	List<String> findByLikeCompanyName(@Param("companyName") String companyName);
	
	@Query("Select c.companyName FROM Company c JOIN c.compstockmap p WHERE p.companyCode LIKE %:CompanyCode%")
	List<String> findByLikeCompanyCode(@Param("CompanyCode") String CompanyCode);
	
	@Query("Select q.name FROM Company c JOIN c.compstockmap p JOIN p.stockexchange q WHERE c.companyName = :CompanyName")
	List<String> findListing(@Param("CompanyName") String CompanyName);
	
	@Query("Select q FROM Company c JOIN c.compstockmap p JOIN p.stockexchange q WHERE c.companyName = :CompanyName")
	List<StockExchange> findListingStockExchange(@Param("CompanyName") String CompanyName);
	
	@Query("Select c FROM Company c JOIN c.compstockmap p WHERE p.companyCode = :CompanyCode")
	Company findByCompanyCode(@Param("CompanyCode") String CompanyCode);
}
