package com.phase3.stockone.controller;

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
import com.phase3.stockone.dao.CompanyStockExchangeRepository;
import com.phase3.stockone.dao.StockExchangeRepository;
import com.phase3.stockone.entities.Company;
import com.phase3.stockone.entities.CompanyStockExchange;
import com.phase3.stockone.entities.StockExchange;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class StockExchangeController {

	@Autowired
	StockExchangeRepository stockExchRepo;
	@Autowired
	CompanyRepository companyRepo;
	@Autowired
	CompanyStockExchangeRepository companyStockExchangeRepository;
	
	// Add - Done
	@PostMapping("/addStockExchange")
	public int addStockExchange(@RequestBody StockExchange stockExchange) {
		stockExchRepo.save(stockExchange);
		return 1;
	}
	
	// get Exchanges -Done
	@GetMapping("/getStockExchanges")
	public List<StockExchange> getStockExchanges(){
		return stockExchRepo.findAll();
	}
	
	// HAVE TO DELETE
	@GetMapping("/getStockExchange")
	public StockExchange getStockExchangeDetails(@RequestParam String name){
		return stockExchRepo.findByName(name);
	}
	// User
	@GetMapping("/getCompanyByExchange")
	public List<String> getCompanyByExchange(@RequestParam String name){
		return stockExchRepo.findCompByExchange(name);
	}
	
	
	/*
	 * Company StockExchange Map Controller
	 */
	// Done
	@PostMapping("/mapStockCompany")
	public int mapStockCompany(@RequestBody Map<String, Object> model) {
		String name=(String)model.get("name");
		String com_name=(String)model.get("com_name");
		
		Company company = companyRepo.findBycompanyName(com_name);
		StockExchange stockExchange = stockExchRepo.findByName(name);
		
		CompanyStockExchange companyStockMap=new CompanyStockExchange();
		companyStockMap.setCompany(company);
		companyStockMap.setStockexchange(stockExchange);
		String companyCode = com_name.substring(0,3)+name+company.getId();
		companyStockMap.setCompanyCode(companyCode);
		
		companyStockExchangeRepository.save(companyStockMap);

		return 5;
	}
	
}
