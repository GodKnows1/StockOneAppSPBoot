package com.phase3.stockone.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "IPODetail")
public class IPODetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private Double pricePerShare;

	@Column(nullable = false)
	private Long totalNumberOfShares;

	private String openDateTime;

	private String companyName;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Company company;

	@ManyToMany
	private List<StockExchange> stockExchanges = new ArrayList<>();
	
	private String remarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPricePerShare() {
		return pricePerShare;
	}

	public void setPricePerShare(Double pricePerShare) {
		this.pricePerShare = pricePerShare;
	}

	public Long getTotalNumberOfShares() {
		return totalNumberOfShares;
	}

	public void setTotalNumberOfShares(Long totalNumberOfShares) {
		this.totalNumberOfShares = totalNumberOfShares;
	}

	public String getOpenDateTime() {
		return openDateTime;
	}

	public void setOpenDateTime(String openDateTime) {
		this.openDateTime = openDateTime;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<StockExchange> getStockExchanges() {
		return stockExchanges;
	}

	public void setStockExchanges(List<StockExchange> stockExchanges) {
		this.stockExchanges = stockExchanges;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public IPODetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public IPODetail(Double pricePerShare, Long totalNumberOfShares, String openDateTime, String companyName,
			String remarks) {
		super();
		this.pricePerShare = pricePerShare;
		this.totalNumberOfShares = totalNumberOfShares;
		this.openDateTime = openDateTime;
		this.companyName = companyName;
		this.remarks = remarks;
	}

	
}
