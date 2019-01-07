package com.xmlpath.examples;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.internal.path.xml.NodeChildrenImpl;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static io.restassured.path.xml.XmlPath.*;

import java.util.List;

public class SearchXMLPathExamples {
	static final String APIKEY = "YOUR_API_KEY";

	@BeforeClass
	public static void init() {

		RestAssured.baseURI = "http://api.walmartlabs.com";
		RestAssured.basePath = "/v1";
	}

	// 1) Extract numItems
	@Test
	public void test001() {
		
	String numItems =			given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "xml")
				.when()
				.get("/search")
				.then()
				.extract()
				.path("searchresponse.numItems");

		System.out.println("------------------StartingTest---------------------------");
		System.out.println("The value of numItems are: "+numItems);
		System.out.println("------------------End of Test---------------------------");
	}

	// 2)Extract first productName by providing list index value
	@Test
	public void test002() {
		
		String productName =	given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "xml")
				.when()
				.get("/search")
				.then()
				.extract()
				.path("searchresponse.items.item[0].name");

		System.out.println("------------------StartingTest---------------------------");
		System.out.println("The product name is: "+productName);
		System.out.println("------------------End of Test---------------------------");
	}

	// 3) Get the gift options for the firstproduct
	@Test
	public void test003() {
		
		String xml =given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "xml")
				.when()
				.get("/search").asString();
		
		String giftOptions = with(xml).getString("searchresponse.items.item[0].giftOptions");
		
		

		System.out.println("------------------StartingTest---------------------------");
		System.out.println("The gift options under the first product are: "+giftOptions);
		System.out.println("------------------End of Test---------------------------");

	}

	// 4) Get the size of items
	@Test
	public void test004() {
		
	NodeChildrenImpl val=	given()
		.queryParam("query", "ipod")
		.queryParam("apiKey", APIKEY)
		.queryParam("format", "xml")
		.when()
		.get("/search")
		.then()
		.extract()
		.path("searchresponse.items.item");

		System.out.println("------------------StartingTest---------------------------");
		System.out.println("The Size of the items are "+val.size());
		System.out.println("------------------End of Test---------------------------");
	}

	// 5) Get All the Names
	@Test
	public void test005() {
		
		String xml =given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "xml")
				.when()
				.get("/search").asString();
		
		List<String> names = with(xml).getList("searchresponse.items.item.name");
		

		System.out.println("------------------StartingTest---------------------------");
		System.out.println("The names of the products are:: "+names);
		System.out.println("------------------End of Test---------------------------");
	}

	// 6) Get the sale price for Name==Apple iPod touch 32GB
	@Test
	public void test006() {

		String xml =given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "xml")
				.when()
				.get("/search").asString();
		
		List<String> salePrice = with(xml).getList("searchresponse.items.item.findAll{it.name=='Apple iPod touch 32GB'}.salePrice");
		
		System.out.println("------------------StartingTest---------------------------");
		System.out.println("The sale price is "+salePrice);
		System.out.println("------------------End of Test---------------------------");
	}

	// 7) Deep Search in XML Path
	@Test
	public void test007() {

		String xml =given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "xml")
				.when()
				.get("/search").asString();
		
		List<String> salePrice = with(xml).getList("**.findAll{it.name=='Apple iPod touch 32GB'}.salePrice");
		System.out.println("------------------StartingTest---------------------------");
		System.out.println("The sale price is "+salePrice);
		System.out.println("------------------End of Test---------------------------");
	}
}
