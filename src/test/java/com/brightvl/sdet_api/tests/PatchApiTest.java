package com.brightvl.sdet_api.tests;

import com.brightvl.sdet_api.pojo.Entity;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class PatchApiTest extends BaseApiTest {

    @Test
    @Description("Тест на обновление сущности через PATCH-запрос")
    public void patchTest() {
        Entity entity = Entity.createDefaultEntity();
        Integer id = createEntity(entity);

        Allure.step("Отправка PATCH-запроса для обновления сущности с ID: " + id);
        given()
                .spec(requestSpecification)
                .body(entity)
                .when().patch("/patch/" + id)
                .then().statusCode(204);

        verifyEntity(id, entity);
    }
}
