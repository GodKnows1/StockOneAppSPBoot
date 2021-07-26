package com.phase3.stockone;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
class CompanyTest {
	
	@Autowired
	MockMvc mvc;
	
	@Test
	 @Order(3)  
	void getAllSec() throws Exception {
		MvcResult m=mvc.perform(MockMvcRequestBuilders.get("/getSectors"))
		.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
		.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[1].sectorName").value("hello"))
		.andReturn();
		String content = m.getResponse().getContentAsString();
		System.out.print("3 "+content);
	}
	
	@Test
	@Order(1) 
	void addSec() throws Exception {
		
		String json="{\"sectorName\":\"hello\",\"brief\":\"hello\"}";
		MvcResult m=mvc.perform(MockMvcRequestBuilders.post("/addSector")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String content = m.getResponse().getContentAsString();
		assertEquals("9",content);
	}
//	
	@Test
	 @Order(2)  
	void getSec() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/getSectorDetailsByName?name=IT"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.brief").value("COMP"));
	}
	
	// Companies
	@Test
	 @Order(8)  
	void getAllCom() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/getCompanies"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[0].companyName").value("ITC"));
	}
	
	@Test
	 @Order(6)  
	void getCom() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/company?name=ITC"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2));
	}
	
	@Test
	 @Order(4)  
	void addCom() throws Exception {
		
		String json="{\"companyName\":\"POP\",\"turnover\":12040,\"ceo\":\"Ayush\",\"boardOfDirectors\":\"Ayush Faddo\",\"companyBrief\":\"veryRich\",\"sectorName\":\"IT\"}";
		MvcResult m=mvc.perform(MockMvcRequestBuilders.post("/addCompany")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String content = m.getResponse().getContentAsString();
		assertEquals("1",content);
	}
	
	@Test
	 @Order(5)  
	void updateCom() throws Exception {
		
		String json="{\"id\":2,\"companyName\":\"ITC\",\"turnover\":144,\"ceo\":\"ABC\",\"boardOfDirectors\":\"XYZ\",\"companyBrief\":\"Hell\",\"sectorName\":\"IT\"}";
		MvcResult  m=mvc.perform(MockMvcRequestBuilders.put("/updateCompany")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String content = m.getResponse().getContentAsString();
		assertEquals("2",content);
	}
	
	@Test
	 @Order(7)  
	void deleteCom() throws Exception {
		
		MvcResult m=mvc.perform(MockMvcRequestBuilders.delete("/deleteCompany/POP"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String content = m.getResponse().getContentAsString();
		assertEquals("-1",content);
	}
	
	@Test
	 @Order(9)  
	void likeCom() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.get("/likeCompany?name=IT"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].companyName").value("ITC"));
	}
	
	// STOCK EXCH
	@Test
	 @Order(10)  
	void addExch() throws Exception {
		
		String json="{ \"name\":\"NSE\",\"brief\":\"bombay\",\"address\":\"mumbai\",\"remarks\":\"Paisa\"}";
		MvcResult m=mvc.perform(MockMvcRequestBuilders.post("/addStockExchange")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String content = m.getResponse().getContentAsString();
		assertEquals("1",content);
	}
	
	
	@Test
	 @Order(11)  
	void getStockExcAll() throws Exception {
		 mvc.perform(MockMvcRequestBuilders.get("/getStockExchanges"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
	}
	
	@Test
	 @Order(12)  
	void mapStock() throws Exception {
		String json="{\"com_name\":\"ITC\",\"name\":\"NSE\"}";
		MvcResult m=mvc.perform(MockMvcRequestBuilders.post("/mapStockCompany")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json))
		.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String content = m.getResponse().getContentAsString();
		assertEquals("5",content);
	}
	
	@Test
	 @Order(13)  
	void getComFromEXCh() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/getCompanyByExchange?name=NSE"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("ITC"));
	}
	
	@Test
	 @Order(14)  
	void getComByCode() throws Exception {
	mvc.perform(MockMvcRequestBuilders.get("/likeCompanyCode?name=ITCNSE2"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].companyName").value("ITC"));
	}
	
	
	@Test
	 @Order(15)  
	void getComFullCode() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/getCompanyCode?name=ITC"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("ITCBSE2"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1]").value("ITCNSE2"));
	}
	
	// Doubt
	
	@Test
	 @Order(16)  
	void getList() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/getListedinExchange/ITC"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("BSE"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1]").value("NSE"));
	}
	
	@Test
	 @Order(17)  
	void addIPO() throws Exception {
		
		String json="{\"remarks\": \"hihihih\",\r\n"
				+ "    \"pricePerShare\": 200.90,\r\n"
				+ "    \"totalNumberOfShares\": 1100,\r\n"
				+ "    \"openDateTime\": \"2022-04-20 09:44:42\",\r\n"
				+ "    \"companyName\": \"ITC\"}";
		MvcResult m=mvc.perform(MockMvcRequestBuilders.post("/addIPO")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String content = m.getResponse().getContentAsString();
		assertEquals("111",content);
	}
	
	@Test
	 @Order(18)  
	void getIPOs() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.get("/getIPOs"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].companyName").value("ITC"));
	}
	
	@Test
	 @Order(19)  
	void getIPOByName() throws Exception {
		
	mvc.perform(MockMvcRequestBuilders.get("/getIPOByCompanyName?companyName=ITC"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].totalNumberOfShares").value(1100));
	}
	@Test
	 @Order(20)  
	void getIPOChrono() throws Exception {
		String json="{\"companyName\":\"POP\",\"turnover\":12040,\"ceo\":\"Ayush\",\"boardOfDirectors\":\"Ayush Faddo\",\"companyBrief\":\"veryRich\",\"sectorName\":\"its\"}";
	mvc.perform(MockMvcRequestBuilders.post("/addCompany")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		String json2="{\"com_name\":\"POP\",\"name\":\"BSE\"}";
		mvc.perform(MockMvcRequestBuilders.post("/mapStockCompany")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json2))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		String json3="{\"remarks\": \"ga\",\r\n"
				+ "    \"pricePerShare\": 200.90,\r\n"
				+ "    \"totalNumberOfShares\": 1100,\r\n"
				+ "    \"openDateTime\": \"2011-04-20 09:44:42\",\r\n"
				+ "    \"companyName\": \"POP\"}";
		mvc.perform(MockMvcRequestBuilders.post("/addIPO")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json3))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		mvc.perform(MockMvcRequestBuilders.get("/getIPOChronologically"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].openDateTime").value("2011-04-20 09:44:42"));
	}
	
	@Test
	 @Order(21)  
	void updateIPO() throws Exception {
		
		String json3="{\"remarks\": \"ga\",\r\n"
				+ "    \"pricePerShare\": 2001.90,\r\n"
				+ "    \"totalNumberOfShares\": 1100,\r\n"
				+ "    \"openDateTime\": \"2011-04-20 09:44:42\",\r\n"
				+ "    \"companyName\": \"POP\"}";
		MvcResult m1=mvc.perform(MockMvcRequestBuilders.put("/updateIPOs")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json3))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		String content = m1.getResponse().getContentAsString();
		assertEquals("21",content);
	}
	
	@Test
	 @Order(22)  
	void adduser() throws Exception {
		
		String json3="{\"name\": \"user\",\r\n"
				+ "    \"password\": \"user\",\r\n"
				+ "    \"email\": \"ga@gmail.com\",\r\n"
				+ "    \"confirmed\": true,\r\n"
				+ "    \"admin\": false,\r\n"
				+ "    \"mobileNum\": \"9999\"}";
		mvc.perform(MockMvcRequestBuilders.post("/setuserapi")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json3))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	 @Order(23)  
	void getusers() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.get("/getUsers")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("user"));
	}
	
	@Test
	 @Order(24)  
	void welcomepage() throws Exception {
		String json="{\"userName\": \"user\"}";
		mvc.perform(MockMvcRequestBuilders.post("/getUserByName")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	 @Order(25)  
	void getuandp() throws Exception {
		
		String json3="{\"name\": \"user1\",\r\n"
				+ "    \"password\": \"user1\",\r\n"
				+ "    \"email\": \"ga@gmail.com\",\r\n"
				+ "    \"confirmed\": true,\r\n"
				+ "    \"admin\": false,\r\n"
				+ "    \"mobileNum\": \"9999\"}";
		mvc.perform(MockMvcRequestBuilders.post("/addUser1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json3))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		String json="{\"name\": \"user1\",\"password\":\"user1\"}";
		mvc.perform(MockMvcRequestBuilders.post("/getUserByNameAndPass")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("user1"));
	}
	
	@Test
	 @Order(26)  
	void getstocks() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.get("/getStockPrices"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].shareprice").value(90.9f));
	}
	
	@Test
	 @Order(27)  
	void getstock() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.get("/getStockPriceFromCompanyName?companyName=ITC"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].shareprice").value(90.9f));
	}
	
	@Test
	 @Order(28)  
	void getstockto() throws Exception {
		String json="{\"fromdate\":\"2011-01-01\",\r\n"
				+ "    \"todate\":\"2020-05-01\",\r\n"
				+ "    \"name\":\"ITC\"}";
		mvc.perform(MockMvcRequestBuilders.post("/fetchToAndFrom")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].shareprice").value(90.9f));
	}
	
	@Test
	 @Order(29)  
	void getsec() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.get("/getListofCompaniesinSector?sectorName=IT"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("ITC"));
	}
	
	@Test
	 @Order(30)  
	void getsecp() throws Exception { 
		
		mvc.perform(MockMvcRequestBuilders.get("/getSectorPriceByName?sectorName=IT"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").value(90.9000015258789d));
	}
	
	@Test
	 @Order(31)  
	void getSectorPriceByPeriod() throws Exception { 
		String json="{\"fromdate\":\"2011-01-01\",\r\n"
				+ "    \"todate\":\"2020-05-01\",\r\n"
				+ "    \"sectorName\":\"IT\"}";
		mvc.perform(MockMvcRequestBuilders.post("/getSectorPriceByPeriod")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").value(90.9000015258789d));
	}
}

