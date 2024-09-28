package com.brightvl.sdet_api.tests;

import com.brightvl.sdet_api.pojo.Entity;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.*;

public class GetApiTest extends BaseApiTest {

    @Test
    @Description("Тест на создание сущности и проверку ее через GET-запрос")
    public void getTest() {
        Entity entity = Entity.createDefaultEntity();
        id = createEntity(entity);

        Allure.step("Отправка GET-запроса для получения сущности с ID: " + id);
        given()
                .spec(requestSpecification)
                .when().get("/get/" + id)
                .then().statusCode(200)
                .body("id", equalTo(id))
                .body("title", equalTo(entity.getTitle()))
                .body("verified", equalTo(entity.isVerified()))
                .body("important_numbers", hasItems(entity.getImportantNumbers().toArray()));
    }

    @Test
    @Description("Тест на получение всех сущностей через GET-запрос")
    public void getAllTest() {
        Allure.step("Отправка GET-запроса для получения всех сущностей");
        given().spec(requestSpecification)
                .when().get("/getAll")
                .then().statusCode(200)
                .rootPath("entity[0]")
                .body("", not(empty()))
                .body("id", notNullValue())
                .body("title", notNullValue())
                .body("verified", notNullValue())
                .body("important_numbers", not(empty()));
    }
}
