package com.phase3.stockone.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phase3.stockone.entities.StockPrice;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Long>{
	
	StockPrice findBycompanycode(String companyCode); // need to change List<StockPrice>
	
	@Query("select s from Company c JOIN c.stockPrice s WHERE c.companyName = :name AND s.datee >=:fromdate AND s.datee <=:todate")
	List<StockPrice> getToAndFrom(@Param("fromdate") Date fromdate, @Param("todate") Date todate, @Param("name") String name);
	
	@Query("select s from Company c JOIN c.stockPrice s WHERE c.id = :id")
	List<StockPrice> getStocksFromCompId(@Param("id") Long id);
	
//	@Query("select s from Company c JOIN c.stockPrice s WHERE s.datee >=:fromdate AND s.datee <=:todate")
//	List<StockPrice> getToAndFrominSector(@Param("fromdate") Date fromdate, @Param("todate") Date todate);

}
