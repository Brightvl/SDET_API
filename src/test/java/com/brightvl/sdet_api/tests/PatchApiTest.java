package com.brightvl.sdet_api.tests;

import com.brightvl.sdet_api.pojo.Response;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PatchApiTest extends BaseApiTest {

    @Test
    @Description("Тест на обновление сущности через PATCH-запрос")
    public void patchTest() {
        Response response = Response.createDefaultResponse();
        id = createEntity(response);

        Allure.step("Отправка PATCH-запроса для обновления сущности с ID: " + id);
        given()
                .spec(requestSpecification)
                .body(Response.createDefaultResponse())
                .when().patch("/patch/" + id)
                .then().statusCode(204);

        Allure.step("Проверка обновленной сущности с ID: " + id);
        given()
                .spec(requestSpecification)
                .when().get("/get/" + id)
                .then().statusCode(200)
                .body("id", notNullValue())
                .body("verified", equalTo(true));
    }
}
