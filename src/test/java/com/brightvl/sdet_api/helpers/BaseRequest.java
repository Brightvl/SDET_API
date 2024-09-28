package com.brightvl.sdet_api.helpers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseRequest {

    public static RequestSpecification initRequestSpecification() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setContentType(ContentType.JSON)
                .setBaseUri("http://localhost:8080/api")
                .setAccept(ContentType.JSON);
        return requestSpecBuilder.build();
    }
}
