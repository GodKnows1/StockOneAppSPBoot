package com.phase3.stockone.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "StockExchange")
public class StockExchange {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String brief;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String remarks;
	
//	@OneToMany(targetEntity = CompanyStockExchange.class)
	@OneToMany(mappedBy  = "stockexchange")
	@JsonIgnore
	private List<CompanyStockExchange> compstockmap;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List<CompanyStockExchange> getCompstockmap() {
		return compstockmap;
	}
	public void setCompstockmap(List<CompanyStockExchange> compstockmap) {
		this.compstockmap = compstockmap;
	}
	public StockExchange(String name, String brief, String address, String remarks) {
		super();
		this.name = name;
		this.brief = brief;
		this.address = address;
		this.remarks = remarks;
	}
	public StockExchange() {
		super();
		// TODO Auto-generated constructor stub
	}


}