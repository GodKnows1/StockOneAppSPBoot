package com.phase3.stockone.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.phase3.stockone.entities.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long>{
	Sector findBySectorName(@RequestParam String name);
}
