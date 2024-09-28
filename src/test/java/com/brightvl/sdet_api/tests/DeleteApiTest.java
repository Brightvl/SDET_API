package com.brightvl.sdet_api.tests;

import com.brightvl.sdet_api.pojo.Entity;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class DeleteApiTest extends BaseApiTest {
    protected Integer id;
    @Test
    @Description("Тест на создание и удаление сущности через DELETE-запрос")
    public void deleteTest() {
        Entity entity = Entity.createDefaultEntity();
        id = createEntity(entity);

        Allure.step("Отправка DELETE-запроса для удаления сущности с ID: " + id);
        deleteEntityById(id);

        Allure.step("Проверка, что сущность с ID: " + id + " была удалена");
        given()
                .spec(requestSpecification)
                .when().get("/get/" + id)
                .then().statusCode(500);
    }

    @Override
    @AfterEach
    public void cleanup() {
        if (id != null) {
            id = null;
        }
    }
}
