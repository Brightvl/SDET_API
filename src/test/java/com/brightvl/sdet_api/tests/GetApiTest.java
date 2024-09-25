package com.brightvl.sdet_api.tests;

import com.brightvl.sdet_api.pojo.Response;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class GetApiTest extends BaseApiTest {

    @Test
    @Description("Тест на создание сущности и проверку ее через GET-запрос")
    public void getTest() {
        Response response = Response.createDefaultResponse();
        id = createEntity(response);

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
}
