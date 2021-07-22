package com.phase3.stockone.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "CompanyStockExchange")
public class CompanyStockExchange {
	public CompanyStockExchange() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String companyCode;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Company company;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private StockExchange stockexchange;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public StockExchange getStockexchange() {
		return stockexchange;
	}

	public void setStockexchange(StockExchange stockexchange) {
		this.stockexchange = stockexchange;
	}

	public CompanyStockExchange(String companyCode) {
		super();
		this.companyCode = companyCode;
	}
	
	
}
