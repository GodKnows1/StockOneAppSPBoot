package com.phase3.stockone.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phase3.stockone.dao.SectorRepository;
import com.phase3.stockone.dao.StockPriceRepository;
import com.phase3.stockone.entities.Company;
import com.phase3.stockone.entities.Sector;
import com.phase3.stockone.entities.StockPrice;


@RestController
@CrossOrigin
public class SectorController {
	
	@Autowired
	SectorRepository sectorRepo;
	
	@Autowired
	StockPriceRepository stockRepo;
	
	// T
	@GetMapping("/getSectorDetailsByName")
	public Sector getSectorByName(@RequestParam String name){
		return sectorRepo.findBySectorName(name);
	}
	
	// T
	@GetMapping("/getSectors")
	public List<Sector> getSectors(){
		return sectorRepo.findAll();
	}
	
	// T
	@PostMapping("/addSector")
	public int addSector(@RequestBody Sector sector) {
		sectorRepo.save(sector);
		return 9;
	}
	
	@GetMapping("/getListofCompaniesinSector")
	public List<String> getListofCompaniesinSector(@RequestParam String sectorName){
		List<Company> listCom = sectorRepo.findBySectorName(sectorName).getCompanies();
		List<String> companies = new ArrayList<String>();
		for(Company company : listCom) {
			companies.add(company.getCompanyName());
		}
		return companies;
	}
	
	@GetMapping("/getSectorPriceByName")
	public double getSectorPrice(@RequestParam String sectorName) {
		List<Company> companies = sectorRepo.findBySectorName(sectorName).getCompanies();
		double amt=0;
		int num=0;
		for(Company c: companies) {
			List<StockPrice> stocks = stockRepo.getStocksFromCompId(c.getId());
			for(StockPrice s:stocks) {
				amt+=s.getShareprice();
				num++;
			}
		}
		amt=amt/num;
		return amt;
	}
	
	@PostMapping("/getSectorPriceByPeriod")
	public double getSectorPriceByPeriod(@RequestBody  Map<String, String> model) throws ParseException {
		
		Date fromdate= new SimpleDateFormat("yyyy-MM-dd").parse(model.get("fromdate"));
		Date todate= new SimpleDateFormat("yyyy-MM-dd").parse(model.get("todate"));
		String sectorName= model.get("sectorName");
		
		List<Company> companies = sectorRepo.findBySectorName(sectorName).getCompanies();
		double amt=0;
		int num=0;
		for(Company c: companies) {
			List<StockPrice> stocks = stockRepo.getToAndFrom(fromdate,todate,c.getCompanyName());
			for(StockPrice s:stocks) {
				amt+=s.getShareprice();
				num++;
			}
		}
		amt=amt/num;
		return amt;
	}
}
