package com.phase3.stockone.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String companyName;

	@Column(nullable = false)
	private Double turnover;

	@Column(nullable = false)
	private String ceo;

	@Column(nullable = false)
	private String boardOfDirectors;

	@Column(nullable = false)
	private String companyBrief;
	
	private String sectorName;

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "company", cascade = CascadeType.REMOVE)
	private IPODetail ipo;

	@OneToMany(mappedBy = "company",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JsonIgnore
	private List<CompanyStockExchange> compstockmap;
	
	@OneToMany(mappedBy = "company",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<StockPrice> stockPrice;

	public List<StockPrice> getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(List<StockPrice> stockPrice) {
		this.stockPrice = stockPrice;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	private Sector sector;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Double getTurnover() {
		return turnover;
	}

	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}

	public String getCeo() {
		return ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
	}

	public String getBoardOfDirectors() {
		return boardOfDirectors;
	}

	public void setBoardOfDirectors(String boardOfDirectors) {
		this.boardOfDirectors = boardOfDirectors;
	}

	public String getCompanyBrief() {
		return companyBrief;
	}

	public void setCompanyBrief(String companyBrief) {
		this.companyBrief = companyBrief;
	}

	public IPODetail getIpo() {
		return ipo;
	}

	public void setIpo(IPODetail ipo) {
		this.ipo = ipo;
	}

	public List<CompanyStockExchange> getCompstockmap() {
		return compstockmap;
	}

	public void setCompstockmap(List<CompanyStockExchange> compstockmap) {
		this.compstockmap = compstockmap;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}
	protected Company() {
	}

	public Company(String companyName, Double turnover, String ceo, String boardOfDirectors, String companyBrief,
			String sectorName) {
		super();
		this.companyName = companyName;
		this.turnover = turnover;
		this.ceo = ceo;
		this.boardOfDirectors = boardOfDirectors;
		this.companyBrief = companyBrief;
		this.sectorName = sectorName;
	}

	
}
