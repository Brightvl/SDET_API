package com.brightvl.sdet_api.tests;

import com.brightvl.sdet_api.helpers.BaseRequest;
import com.brightvl.sdet_api.pojo.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.notNullValue;

public class ApiCrudTest {

    private String id;

    @BeforeAll
    public static void setup() {
        requestSpecification = BaseRequest.initRequestSpecification();
    }

    @Test
    public void createAndVerifyEntity() {
        // 1. Создание новой сущности через POST-запрос
        Response response = Response.createDefaultResponse();

        id = given()
                .spec(requestSpecification)
                .body(response)
                .when()
                .post("/create")
                .then()
                .statusCode(200)
                .extract().asString();

        // 2. Проверка, что сущность была успешно создана через GET-запрос
        given()
                .spec(requestSpecification)
                .when()
                .get("/get/" + id)
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("title", notNullValue());
    }

    @AfterEach
    public void cleanup() {
        // 3. Удаление созданной сущности после тестов
        if (id != null) {
            BaseRequest.deleteTestDataById(id);
        }
    }
}
