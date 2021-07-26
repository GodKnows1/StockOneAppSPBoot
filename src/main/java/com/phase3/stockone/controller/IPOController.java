package com.phase3.stockone.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phase3.stockone.dao.CompanyRepository;
import com.phase3.stockone.dao.IPODetailRepository;
import com.phase3.stockone.entities.Company;
import com.phase3.stockone.entities.IPODetail;


@RestController
@CrossOrigin
public class IPOController {

	@Autowired
	private IPODetailRepository ipoRepo;

	@Autowired
	private CompanyRepository companyRepo;

//	@PostMapping("/addIPO")
//	public int addIPODetails(@RequestBody Map<String, Object> model) {
//		String company_name=(String)model.get("company_name");
//		Company company = companyRepo.findBycompanyName(company_name);
//		
//		ObjectMapper objectMapper = new ObjectMapper();
//		IPODetail ipodetail = objectMapper.convertValue(model.get("ipo_name"), IPODetail.class);
//		ipodetail.setStockExchanges(companyRepo.findListingStockExchange(company_name));
//		ipodetail.setCompany(company);
//		ipoRepo.save(ipodetail);
//		return 111;
//	}
	// DOne
	@PostMapping("/addIPO")
	public int addIPODetails(@RequestBody IPODetail model) {
		String company_name = model.getCompanyName();
		Company company = companyRepo.findBycompanyName(company_name);
		model.setStockExchanges(companyRepo.findListingStockExchange(company_name));
		model.setCompany(company);
		ipoRepo.save(model);
		return 111;
	}

	// Done
	@GetMapping("/getIPOs")
	public List<IPODetail> getIPODetails() {
		return ipoRepo.findAll();
	}

	@PutMapping("/updateIPOs")
	public int updateIPOs(@RequestBody IPODetail model) {
		String company_name = model.getCompanyName();
		Company company = companyRepo.findBycompanyName(company_name);
		model.setStockExchanges(companyRepo.findListingStockExchange(company_name));
		model.setCompany(company);
		ipoRepo.save(model);
		return 21;
	}

	@GetMapping("/getIPOByCompanyName")
	public List<IPODetail> getIPODetailsByCompanyName(@RequestParam String companyName) {
		Company company = companyRepo.findBycompanyName(companyName);
		Long companyId = company.getId();
		return ipoRepo.findIPOByCompanyId(companyId);
	}

	@GetMapping("/getIPOChronologically")
	public List<IPODetail> getIPOsChronologically() {
		return ipoRepo.getIPOChronologically();
	}
}