package com.brightvl.sdet_api.tests;

import com.brightvl.sdet_api.pojo.Response;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class DeleteApiTest extends BaseApiTest {

    @Test
    @Description("Тест на создание и удаление сущности через DELETE-запрос")
    public void deleteTest() {
        Response response = Response.createDefaultResponse();
        id = createEntity(response);

        Allure.step("Отправка DELETE-запроса для удаления сущности с ID: " + id);
        given().spec(requestSpecification)
                .when()
                .delete("/delete/" + id)
                .then()
                .statusCode(204);
    }

    @AfterEach
    @Description("Очистка тестовых данных")
    public void cleanup() {
        if (id != null) {
            id = null;
        }
    }
}
