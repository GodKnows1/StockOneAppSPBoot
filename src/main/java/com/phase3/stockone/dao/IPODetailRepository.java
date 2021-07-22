package com.phase3.stockone.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phase3.stockone.entities.IPODetail;

@Repository
public interface IPODetailRepository extends JpaRepository<IPODetail, Long>{
	@Query("Select i FROM IPODetail i JOIN i.company c WHERE c.id = :id")
	List<IPODetail> findIPOByCompanyId(@Param("id") Long id);
	
	@Query("select i from IPODetail i order by cast(i.openDateTime as timestamp)")
	List<IPODetail> getIPOChronologically();
}
