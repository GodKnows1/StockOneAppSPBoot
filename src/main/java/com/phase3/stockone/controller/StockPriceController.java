package com.phase3.stockone.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phase3.stockone.dao.CompanyRepository;
import com.phase3.stockone.dao.StockPriceRepository;
import com.phase3.stockone.entities.Company;
import com.phase3.stockone.entities.StockPrice;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class StockPriceController {
	
	@Autowired
	StockPriceRepository stockPriceRepo;
	
	@Autowired
	CompanyRepository companyRepo;
	// Admin
	@PostMapping("/addStockprice")
	public int addStockPrice(@RequestBody StockPrice stockPriceJson) {
		Company company = companyRepo.findByCompanyCode(stockPriceJson.getCompanycode());
		stockPriceJson.setCompany(company);
		stockPriceRepo.save(stockPriceJson);
		return 1;
	}
	// Admin
	@GetMapping("/getStockPrices")
	public List<StockPrice> getStockPrices(){
		return stockPriceRepo.findAll();
	}
	
	@GetMapping("/getStockPrice")
	public StockPrice getStockPrice(@RequestParam String companyCode){
		return stockPriceRepo.findBycompanycode(companyCode);
	}
	
	@GetMapping("/getStockPriceFromCompanyName")
	public List<StockPrice> getStockPriceFromCompanyName(@RequestParam String companyName) {
		
		Company company = companyRepo.findBycompanyName(companyName);
		return stockPriceRepo.getStocksFromCompId(company.getId());
	}
	
	@PostMapping("/fetchToAndFrom")
	public List<StockPrice> fetchStockToAndFrom(@RequestBody Map<String, String> model) throws ParseException{
		String fromdate= model.get("fromdate");
		String todate= model.get("todate");
		String name= model.get("name");
		return stockPriceRepo.getToAndFrom(new SimpleDateFormat("yyyy-MM-dd").parse(fromdate),new SimpleDateFormat("yyyy-MM-dd").parse(todate),name);
	}
}
