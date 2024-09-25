package com.brightvl.sdet_api.tests;

import com.brightvl.sdet_api.helpers.BaseRequest;
import com.brightvl.sdet_api.pojo.Response;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public abstract class BaseApiTest {
    protected volatile String id;

    @BeforeAll
    public static void setup() {
        requestSpecification = BaseRequest.initRequestSpecification();
    }

    protected String createEntity(Response response) {
        Allure.step("Отправка POST-запроса для создания сущности с телом: " + response);
        return given()
                .spec(requestSpecification)
                .body(response)
                .when()
                .post("/create")
                .then().statusCode(200)
                .extract()
                .asString();
    }

    protected void deleteEntity(String entityId) {
        if (entityId != null) {
            Allure.step("Удаление сущности с ID: " + entityId);
            BaseRequest.deleteTestDataById(entityId);
        }
    }

    @AfterEach
    public void cleanup() {
        deleteEntity(id);
        id = null;
    }
}
