package com.brightvl.sdet_api.tests;

import com.brightvl.sdet_api.pojo.Entity;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

public class PostApiTest extends BaseApiTest {

    @Test
    @Description("Тест на создание сущности через POST-запрос и проверка статус-кода")
    public void postTest() {
        Entity entity = Entity.createDefaultEntity();

        Integer id = createEntity(entity);

        verifyEntity(id, entity);
    }
}
