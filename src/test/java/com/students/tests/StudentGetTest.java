package com.students.tests;

import com.student.model.Student;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

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
                .param("programme", "Medicine")
                .param("limit", 2)
            .when()
            .get("/list");

     //   System.out.println(response.body().prettyPrint());
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
        Student student = new Student();

        System.out.println("<<< ------ Logging getStudentFromFA method ----- >>>");
        given().contentType(ContentType.JSON)

            .param("programme", "Medicine")
            .param("limit", 2)

                .log().all()
            .when()
         //       .body(student)
            .get("/list")
            .then()
                .log().all()
            .statusCode(200);
    }


}
