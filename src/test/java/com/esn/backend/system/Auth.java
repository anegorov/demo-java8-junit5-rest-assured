package com.esn.backend.system;

import com.esn.backend.common.Call;
import com.esn.backend.model.AuthFail;
import com.esn.backend.model.AuthSuccess;

import java.util.HashMap;

public class Auth extends Call {
    public void login(final String login, final String password) {
        login(new HashMap<String, String>() {{
            put("login", login);
            put("password", password);
        }}, "/login");
    }

    public void login(final String login, final String password, String src) {
        login(new HashMap<String, String>() {{
            put("login", login);
            put("password", password);
        }}, src);
    }

    public void login(final String login) {
        login(new HashMap<String, String>() {{
            put("login", login);
        }}, "/login");
    }

    private void login(HashMap<String, String> credentials, String src) {
        validatableResponse = baseRequestSpec()
                .body(credentials)
                .when()
                .post(src)
                .then()
                .log().all();
    }

    public AuthSuccess getSuccessMessage(){
        return validatableResponse.extract().body().as(AuthSuccess.class);
    }

    public AuthFail getFailedMessage(){
        return validatableResponse.extract().body().as(AuthFail.class);
    }
}