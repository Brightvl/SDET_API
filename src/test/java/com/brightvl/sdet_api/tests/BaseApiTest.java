package com.brightvl.sdet_api.tests;

import com.brightvl.sdet_api.helpers.BaseRequest;
import com.brightvl.sdet_api.pojo.Entity;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public abstract class BaseApiTest {
    protected volatile Integer id;

    @BeforeAll
    public static void setup() {
        requestSpecification = BaseRequest.initRequestSpecification();
    }

    @Step("Отправка POST-запроса для создания сущности с телом: {entity}")
    protected Integer createEntity(Entity entity) {
        return Integer.parseInt(given()
                .spec(requestSpecification)
                .body(entity)
                .when().post("/create")
                .then().statusCode(200)
                .extract()
                .asString());
    }

    @Step("Проверка свойств сущности с ID: {id}")
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
        if (id != null) {
            deleteEntityById(id);
            id = null;
        }
    }

    @Step("Удаление сущности с ID: {id}")
    protected void deleteEntityById(Integer id) {
        given()
                .when()
                .delete("/delete/" + id)
                .then()
                .statusCode(204);
    }
}
