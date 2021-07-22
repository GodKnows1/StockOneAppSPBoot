package com.phase3.stockone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.phase3.stockone.controller.CompanyController;
import com.phase3.stockone.entities.Company;
@RunWith(SpringRunner.class)
@WebMvcTest(CompanyController.class)
public class CompanyTest {
	
	@Autowired
    private MockMvc mvc;

    @MockBean
    private CompanyController companyController;
    
	@Test
	public void getCompanyDetailsById() {
		Company company= new Company("ITC", 1235.90, "Gaurav", "Ayush Faddo", "Big", "health");
		
		
	}

}
