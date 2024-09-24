package com.brightvl.sdet_api.tests;

import com.brightvl.sdet_api.pojo.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class DeleteApiTest extends ApiTestsBase {

    @Test
    public void deleteTest() {
        Response response = Response.createDefaultResponse();

        String id = given()
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
                .delete("/delete/" + id)
                .then()
                .statusCode(204);
    }

}
