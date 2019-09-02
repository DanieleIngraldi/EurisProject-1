package com.euris.dbexample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DbexampleApplicationTests {
	@Autowired
	private MockMvc mvc;
	@Autowired
	private CustomerRepository repo;

	private static final String BASE_URL = "/customer/";
	private static final int REPO_SIZE = 3;


	@org.junit.Test
	public void contextLoad() {
		assertNotNull(mvc);
	}


	@org.junit.Test
	public void retrieveAllRecords() throws Exception {
		MvcResult result = mvc.perform(get(BASE_URL).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(REPO_SIZE)))
				.andReturn();
	}

	@Test
	public void getCustomerById() throws Exception {
		Long id = repo.findAll().iterator().next().getId();

		MvcResult result = mvc.perform(get(BASE_URL + "{id}", id.intValue()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(id.intValue())))
				.andReturn();
	}
}
