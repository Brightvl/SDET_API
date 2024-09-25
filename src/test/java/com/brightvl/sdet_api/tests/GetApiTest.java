package com.brightvl.sdet_api.tests;

import com.brightvl.sdet_api.helpers.BaseRequest;
import com.brightvl.sdet_api.pojo.Response;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
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
    @Description("Тест на создание сущности и проверку ее через GET-запрос")
    public void getTest() {
        Response response = Response.createDefaultResponse();

        Allure.step("Отправка POST-запроса для создания сущности с телом: " + response.toString());
        id = given()
                .spec(requestSpecification)
                .body(response)
                .when()
                .post("/create")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        Allure.step("Отправка GET-запроса для получения сущности с ID: " + id);
        given()
                .spec(requestSpecification)
                .when()
                .get("/get/" + id)
                .then()
                .statusCode(200);
    }

    @Test
    @Description("Тест на получение всех сущностей через GET-запрос")
    public void getAllTest() {
        Allure.step("Отправка GET-запроса для получения всех сущностей");
        given()
                .spec(requestSpecification)
                .when()
                .get("/getAll")
                .then()
                .statusCode(200);
    }

    @AfterEach
    public void cleanup() {
        if (id != null) {
            Allure.step("Удаление тестовых данных с ID: " + id);
            BaseRequest.deleteTestDataById(id);
            id = null;
        }
    }


}
