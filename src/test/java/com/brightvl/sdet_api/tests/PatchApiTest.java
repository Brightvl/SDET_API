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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PatchApiTest {
    private String id;

    @BeforeAll
    public static void setup() {
        requestSpecification = BaseRequest.initRequestSpecification();
    }

    @Test
    @Description("Тест на обновление сущности через PATCH-запрос")
    public void patchTest() {
        Response response = Response.createDefaultResponse();
        Allure.step("Отправка POST-запроса для создания сущности");
        id = given()
                .spec(requestSpecification)
                .body(response)
                .when()
                .post("/create")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        Allure.step("Отправка PATCH-запроса для обновления сущности с ID: " + id);
        given()
                .spec(requestSpecification)
                .body(generatePatchMessage())
                .when()
                .patch("/patch/" + id)
                .then()
                .statusCode(204);

        Allure.step("Проверка обновленной сущности с ID: " + id);
        given()
                .spec(requestSpecification)
                .when()
                .get("/get/" + id)
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("verified", equalTo(true));
    }
    @Step("Генерация PATCH-сообщения для обновления сущности")
    private String generatePatchMessage() {
        return """
            {
              "important_numbers": [42, 87, 15],
              "title": "Заголовок сущности",
              "verified": true,
              "addition": {
                "additional_info": "Дополнительные сведения",
                "additional_number": 123
              }
            }
            """;
    }

    @AfterEach
    @Step("Удаление тестовых данных с ID: {id}")
    public void cleanup() {
        if (id != null) {
            BaseRequest.deleteTestDataById(id);
            id = null;
        }
    }
}
