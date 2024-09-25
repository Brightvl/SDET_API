package com.brightvl.sdet_api.tests;

import com.brightvl.sdet_api.helpers.BaseRequest;
import com.brightvl.sdet_api.pojo.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class GetApiTest {

    private String id;

    @BeforeAll
    public static void setup() {
        requestSpecification = BaseRequest.initRequestSpecification();
    }

    @Test
    public void getTest() {
        Response response = Response.createDefaultResponse();

        id = given()
                .spec(requestSpecification)
                .body(response)
                .when()
                .post("/create")
                .then()
                .statusCode(200)
                .extract()
                .asString();
        System.out.println(id);

        given()
                .spec(requestSpecification)
                .when()
                .get("/get/" + id)
                .then()
                .statusCode(200);
    }

    @AfterEach
    public void cleanup() {
        if (id != null) {
            BaseRequest.deleteTestDataById(id);
            id = null;
        }
    }

    @Test
    public void getAllTest() {
        given()
                .spec(requestSpecification)
                .when()
                .get("/getAll")
                .then()
                .statusCode(200);
    }
}
