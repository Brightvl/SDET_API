package com.brightvl.sdet_api.tests;

import com.brightvl.sdet_api.pojo.Response;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

public class PostApiTest extends BaseApiTest {

    @Test
    @Description("Тест на создание сущности через POST-запрос и проверка статус-кода")
    public void postTest() {
        Response response = Response.createDefaultResponse();

        Allure.step("Отправка POST-запроса для создания сущности с телом: " + response.toString());
        id = createEntity(response);
    }
}
