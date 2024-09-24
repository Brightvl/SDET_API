package com.brightvl.sdet_api.tests;

import com.brightvl.sdet_api.helpers.BaseRequest;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.requestSpecification;

public abstract class ApiTestsBase {



    @BeforeAll
    static void beforeAll() {
        requestSpecification = BaseRequest.initRequestSpecification();
    }
}
