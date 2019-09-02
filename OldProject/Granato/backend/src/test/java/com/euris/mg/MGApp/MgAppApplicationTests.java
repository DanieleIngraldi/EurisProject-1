package com.euris.mg.MGApp;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.HashMap;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertTrue;

//TODO v4.4.0 gives error, further investigation needed
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MgAppApplicationTests {

	@LocalServerPort
	int port;

	@Before
	public void setUp() throws Exception {
		RestAssured.port = port;
	}

	@Autowired
	public ApplicationContext context;

						// GENERIC BEAN TEST
	@Test
	public void contextLoads() {
		assertThat(context).isNotNull();
	}

						// API TESTS
	@Test
	//@DisplayName("Rest call returns a JSON")
	public void restApiReturnsJson(){
		RestAssured.baseURI = "http://localhost";
		RequestSpecification httpRequest = given();
		// Set HTTP Headers
		httpRequest.header("Content-Type", "application/json");
		Response response = httpRequest.get("/api/getall");
		// Get JSON Representation from Response Body
		assertTrue(response.getContentType().equals("application/json;charset=UTF-8"));
	}

	@Test
	//@DisplayName("Five elements are written in H2 DB")
	public void dbContainsExpectedElements(){
		RestAssured.baseURI = "http://localhost";
		RequestSpecification httpRequest = given();
		// Set HTTP Headers
		httpRequest.header("Content-Type", "application/json");
		Response response = httpRequest.get("/api/getall");
		// Get JSON Representation from Response Body
		JsonPath jsonPathEvaluator = response.jsonPath();
		assertTrue(jsonPathEvaluator.getList("$").size() == 6);
	}

	@Test
	//@DisplayName("Search Jack and Chloe in H2 DB")
	public void matchNamesOnJson(){
		get("/api/getall").then().body("name", hasItems("Jack", "Chloe"));
	}

	@Test
	public void getPersonsReturns200(){
		RestAssured.baseURI = "http://localhost";

		given().
				contentType("application/json").
				get("/api/getall").
				then().
				statusCode(200);
	}

	@Test
	public void postReturnsCode201(){
		RestAssured.baseURI = "http://localhost";

		var jsonBody = new HashMap<String, Object>();
		jsonBody.put("name","michele");
		jsonBody.put("surname","g");
		jsonBody.put("fiscalCode","dsfdsf");
		jsonBody.put("birthDate",LocalDate.of(1985, 07, 06));
		given().
				contentType("application/json").
				body(jsonBody).
				post("/api/newperson").
				then().
				statusCode(201);
	}
}
