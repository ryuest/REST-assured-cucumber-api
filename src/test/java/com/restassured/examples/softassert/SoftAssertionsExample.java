package com.restassured.examples.softassert;

import org.junit.Test;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;

public class SoftAssertionsExample {
	@Test
	public void hardAsserts(){
		RestAssured.given()
		.when()
		.get("http://localhost:8085/student/list")
		.then()
		.body("[0].firstName",equalTo("Vernon"))
		.body("[0].lastName",equalTo("Harper"))
		.body("[0].email",equalTo("egestas.rhoncus.Proin@massaQuisqueporttitor.org"))
		.body("[0].programme",equalTo("Financial Analysis"));
		
	}
	
	@Test
	public void softAsserts(){
		RestAssured.given()
		.when()
		.get("http://localhost:8085/student/list")
		.then()
		.body("[0].firstName",equalTo("Vernonw"),
				"[0].lastName",equalTo("Harper"),
				"[0].email",equalTo("egestas.rhoncus.Proin@massaQuisqueporttitor.orge"),
				"[0].programme",equalTo("Financial Analysis"));
		
		
	}
}
