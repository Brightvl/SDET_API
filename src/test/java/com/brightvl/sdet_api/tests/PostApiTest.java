package com.brightvl.sdet_api.tests;

import com.brightvl.sdet_api.helpers.BaseRequest;
import com.brightvl.sdet_api.pojo.Response;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class PostApiTest {
    private String id;

    @BeforeAll
    public static void setup() {
        requestSpecification = BaseRequest.initRequestSpecification();
    }

    @Test
    @Description("Тест на создание сущности через POST-запрос и проверка статус-кода")
    public void postTest() {
        Response response = Response.createDefaultResponse();

        Allure.step("Отправка POST-запроса для создания сущности с телом: " + response.getTitle());
        given()
                .spec(requestSpecification)
                .body(response)
                .when()
                .post("/create")
                .then()
                .statusCode(200)
                .extract().asString();
    }

    @AfterEach
    @Step("Удаление тестовых данных")
    public void cleanup() {
        if (id != null) {
            BaseRequest.deleteTestDataById(id);
            id = null;
        }
    }
}
