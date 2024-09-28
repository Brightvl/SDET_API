package com.brightvl.sdet_api.tests;

import com.brightvl.sdet_api.helpers.BaseRequest;
import com.brightvl.sdet_api.pojo.Entity;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public abstract class BaseApiTest {
    protected final List<Integer> createdEntityIds = new ArrayList<>();

    @BeforeAll
    public static void setup() {
        requestSpecification = BaseRequest.initRequestSpecification();
    }

    @Step("Отправка POST-запроса для создания сущности")
    protected Integer createEntity(Entity entity) {
        Integer entityId = Integer.parseInt(given()
                .spec(requestSpecification)
                .body(entity)
                .when().post("/create")
                .then().statusCode(200)
                .extract()
                .asString());
        createdEntityIds.add(entityId);
        return entityId;
    }

    @Step("Проверка свойств сущности")
    protected void verifyEntity(Integer id, Entity entity) {
        given()
                .spec(requestSpecification)
                .when().get("/get/" + id)
                .then().statusCode(200)
                .body("id", equalTo(id))
                .body("title", equalTo(entity.getTitle()))
                .body("verified", equalTo(entity.isVerified()))
                .body("important_numbers", hasItems(entity.getImportantNumbers().toArray()));
    }

    @AfterEach
    @Description("Очистка тестовых данных")
    public void cleanup() {
        for (Integer entityId : new ArrayList<>(createdEntityIds)) {
            deleteEntityById(entityId);
            createdEntityIds.remove(entityId);
        }
    }

    @Step("Удаление сущности")
    protected void deleteEntityById(Integer id) {
        given()
                .when()
                .delete("/delete/" + id)
                .then()
                .statusCode(204);
    }
}
