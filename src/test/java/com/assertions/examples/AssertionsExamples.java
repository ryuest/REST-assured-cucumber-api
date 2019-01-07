package com.assertions.examples;

import static io.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;

public class AssertionsExamples {
	static final String APIKEY = "82smmububj5ahsvq35vq35qz";

	@BeforeClass
	public static void init() {

		RestAssured.baseURI = "http://api.walmartlabs.com";
		RestAssured.basePath = "/v1";
	}

	// 1) Verify if the number of items is equal to 10
	@Test
	public void test001() {
		given()
				.queryParam("query","ipod")
				.queryParam("apiKey",APIKEY)
				.queryParam("format","json")
				.when()
				.get("/search")
				.then()
				.body("numItems", equalTo(10));
		
	}

	// 2) Verify Query
	@Test
	public void test002() {
		given()
		.queryParam("query","ipod")
		.queryParam("apiKey",APIKEY)
		.queryParam("format","json")
		.when()
		.get("/search")
		.then()
		.body("query", equalToIgnoringCase("IPOD"));
	}


	// 3) Check Single Name in ArrayList
	@Test
	public void test003() {
		given()
		.queryParam("query","ipod")
		.queryParam("apiKey",APIKEY)
		.queryParam("format","json")
		.when()
		.get("/search")
		.then()
		.body("items.name",hasItem("Refurbished Apple iPod nano 16GB, Blue"));
	}

	// 4) Check Multiple Names in ArrayList
	@Test
	public void test004() {
		given()
		.queryParam("query","ipod")
		.queryParam("apiKey",APIKEY)
		.queryParam("format","json")
		.when()
		.get("/search")
		.then()
		.body("items.name",hasItems("Refurbished Apple iPod nano 16GB, Blue","Apple iPod Touch 6th Generation 16GB Refurbished"));
	}
	
	// 5) Verify the gift options for the first product (Checking Values inside Map using hasValue())
		@Test
		public void test005() {
			given()
			.queryParam("query","ipod")
			.queryParam("apiKey",APIKEY)
			.queryParam("format","json")
			.when()
			.get("/search")
			.then()
			.body("items[0].giftOptions", hasKey("allowGiftWrap"));
			}

	// 6) Check hashmap values inside a list
	@Test
	public void test006() {

				given()
				.queryParam("query","ipod")
				.queryParam("apiKey",APIKEY)
				.queryParam("format","json")
				.when()
				.get("/search")
				.then()
				.body("items.findAll{it.name=='Apple iPod touch 32GB'}", hasItems(hasEntry("name", "Apple iPod touch 32GB")));
				
	}
	

	// 7) Checking multiple values in the same statement
	@Test
	public void test007() {
		given()
		.queryParam("query","ipod")
		.queryParam("apiKey",APIKEY)
		.queryParam("format","json")
		.when()
		.get("/search")
		.then()
		.body("items[0].giftOptions", hasKey("allowGiftWrap"))
		.body("items.findAll{it.name=='Apple iPod touch 32GB'}", hasItems(hasEntry("name", "Apple iPod touch 32GB")))
		.body("items.name",hasItem("Refurbished Apple iPod nano 16GB, Blue"))
		.statusCode(200);
	}

	// 8) Logical Assertions
	@Test
	public void test008() {
		given()
		.queryParam("query","ipod")
		.queryParam("apiKey",APIKEY)
		.queryParam("format","json")
		.when()
		.get("/search")
		.then()
		.body("items.size()",equalTo(10))
		.body("items.size()",greaterThan(5))
		.body("items.size()",lessThan(11))
		.body("items.size()",greaterThanOrEqualTo(10))
		.body("items.size()",lessThanOrEqualTo(10));
			
	}

}
