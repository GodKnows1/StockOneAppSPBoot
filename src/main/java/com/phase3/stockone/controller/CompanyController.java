package com.phase3.stockone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phase3.stockone.dao.CompanyRepository;
import com.phase3.stockone.dao.SectorRepository;
import com.phase3.stockone.entities.Company;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CompanyController {

	
	@Autowired
	private CompanyRepository companyRepo;
	
	@Autowired
	private SectorRepository sectorRepo;
	
	// User
	@GetMapping("/company")
	public Company getCompanyDetailsById(@RequestParam String name){
		Company company = companyRepo.findBycompanyName(name);
		return company;
	}
	// Add Company - Done
	@PostMapping("/addCompany")
	public int addCompanyDetails(@RequestBody Company company) {
		company.setSector(sectorRepo.findBySectorName(company.getSectorName()));
		companyRepo.save(company);
		return 1;
	}
	// Update Company -Done
	@PutMapping("/updateCompany")
	public int updateCompanyDetails(@RequestBody Company company) {
		companyRepo.save(company);
		return 2;
	}
	// Delete Company -Done
	@DeleteMapping("/deleteCompany/{name}")
	public int deleteCompany(@PathVariable String name) {
		Company company = companyRepo.findBycompanyName(name);
		companyRepo.delete(company);
		return -1;
	}
	
	// User
	@GetMapping("/likeCompany/{name}")
	public List<String> getCompanyByName(@PathVariable String name){
		List<String> listCompany = companyRepo.findByLikeCompanyName(name);
		return listCompany;
	}
	
	// User
	@GetMapping("/likeCompanyCode/{name}")
	public List<String> getCompanyByCode(@PathVariable String name){
		List<String> listCompany = companyRepo.findByLikeCompanyCode(name);
		return listCompany;
	}
	
	// TODO
	@PostMapping("/getListedinExchange/{name}")
	public List<String> getListedinExchange(@PathVariable String name){
		return companyRepo.findListing(name);
	}
	
	// get All Companies- DONE
	@GetMapping("/getCompanies")
	public List<Company> getCompanies(){
		return companyRepo.findAll();
	}
}
