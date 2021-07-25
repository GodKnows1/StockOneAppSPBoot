package com.phase3.stockone;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.phase3.stockone.controller.CompanyController;
import com.phase3.stockone.controller.IPOController;
import com.phase3.stockone.controller.SectorController;
import com.phase3.stockone.controller.StockExchangeController;
import com.phase3.stockone.controller.StockPriceController;
import com.phase3.stockone.entities.Company;
import com.phase3.stockone.entities.IPODetail;
import com.phase3.stockone.entities.Sector;
import com.phase3.stockone.entities.StockExchange;
import com.phase3.stockone.entities.StockPrice;

@SpringBootApplication
public class StockoneApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(StockoneApplication.class, args);
	}
	
//	@Bean
//	public CommandLineRunner loadData(CompanyController c,StockPriceController st, StockExchangeController s,IPOController i,SectorController sc) {
//	    return (args) -> {
//	        // save a couple of customers
//	    	sc.addSector(new Sector("JACK", "Bauer"));
////	        r.save(new Sector("JACK", "Bauer"));
////	        System.out.println(r.findAll());
//	    	c.addCompanyDetails(new Company("ITC", 123.9, "ABC", "XYZ", "Hell", "JACK"));
////	        c.save(new Company("ITC", 123.9, "ABC", "XYZ", "Hell", "JACK"));
////	        System.out.print(c.findAll());
//	    	s.addStockExchange(new StockExchange("BSE","PAISA","BOM","Rem"));
////	        s.save(new StockExchange("BSE","PAISA","BOM","Rem"));
//	    	Map<String,String> map=new HashMap<String,String>(); 
//	    	map.put("com_name", "ITC");
//	    	map.put("name", "BSE");
//	    	s.mapStockCompany(map);
//	    	
////	    	i.addIPODetails(new IPODetail(20.0, 11l, "2020-01-01 10:09:09", "ITC", "hihi"));
//	    	
//	    	st.addStockPrice(new StockPrice("BSE", "ITCBSE2", new SimpleDateFormat("yyyy-MM-dd").parse("2019-09-11"), new Time(10,20,30), 90.9f));
//	        
//	    };
//	}

}
