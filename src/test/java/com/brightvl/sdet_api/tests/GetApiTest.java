package com.brightvl.sdet_api.tests;

import com.brightvl.sdet_api.helpers.BaseRequest;
import com.brightvl.sdet_api.pojo.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class GetApiTest extends ApiTestsBase {
    private String id;
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
                .extract().asString();

        given()
                .spec(requestSpecification)
                .when()
                .get("/get/" + id)
                .then()
                .statusCode(200);
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

    @AfterEach
    public void deleteTestsDataById() {
        if (id != null) {
            BaseRequest.deleteTestDataById(id);
        }
    }
}
