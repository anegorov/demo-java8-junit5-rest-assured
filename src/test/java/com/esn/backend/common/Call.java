package com.esn.backend.common;

import com.esn.backend.config.TestConfig;
import com.esn.backend.model.AuthSuccess;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class Call {
    protected ValidatableResponse validatableResponse;

    protected RequestSpecification baseRequestSpec() {
        return RestAssured
                .given()
                .urlEncodingEnabled(false)
                .baseUri(TestConfig.getBase())
                .contentType(ContentType.JSON);
    }

    public int getStatusCode(){
        return validatableResponse.extract().statusCode();
    }

    public String getBodyContent(String path){
        return validatableResponse.extract().body().path(path);
    }

}
