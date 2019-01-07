package com.students.tests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class StudentGetTest {

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8085;
        RestAssured.basePath = "/student";
    }

    @Test
    public void getAllStudentInformation() {

        Response response = given()
            .when()
            .get("/list");

      //  System.out.println(response.body().prettyPrint());
    }

    @Test
    public void getStudentInfo() {
        given()
            .when()
            .get("/1")
            .then()
            .statusCode(200);
    }

    @Test
    public void getStudentFromFA() {
        given()
            .param("programme", "Financial Analysis")
            .param("limit", 2)
            .when()
            .get("/list")
            .then()
            .statusCode(200);
    }


}
