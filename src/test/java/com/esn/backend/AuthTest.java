package com.esn.backend;

import com.esn.backend.system.Auth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AuthTest {

    private Auth auth;

    @BeforeEach
    void setUp() {
        auth =  new Auth();
    }

    @Tag("positive")
    @DisplayName("Should login with correct credentials")
    @Test
    void successfulAuth() {
        auth.login("admin", "admin");

        assertThat(auth.getStatusCode()).isEqualTo(200);
        assertThat(auth.getSuccessMessage().getLogin()).isEqualTo("admin");
        assertThat(auth.getSuccessMessage().getToken().length()).isGreaterThan(20);
        assertThat(auth.getSuccessMessage().getRefreshToken().length()).isGreaterThan(20);
        assertThat(auth.getSuccessMessage().getExpiredAt()).isAfter("2022-03-01");
    }

    @Tag("negative")
    @DisplayName("Should get code 400 with with Bad Request")
    @Test
    void failedAuthBadRequest() {
        auth.login("admin");

        assertThat(auth.getStatusCode()).isEqualTo(400);
        assertThat(auth.getFailedMessage().getError()).isEqualTo("Bad Request");
        assertThat(auth.getFailedMessage().getMessage()).isEqualTo("The request has to contain password");
        assertThat(auth.getFailedMessage().getTimestamp()).isAfter("2022-03-01");
        assertThat(auth.getFailedMessage().getStatus()).isEqualTo(3);
    }

    @Tag("negative")
    @DisplayName("Should get 401 with wrong password")
    @Test
    void failedAuthWrongPassword() {
        auth.login("user", "user");

        assertThat(auth.getStatusCode()).isEqualTo(401);
        assertThat(auth.getFailedMessage().getError()).isEqualTo("Unauthorized");
        assertThat(auth.getFailedMessage().getMessage()).isEqualTo("Wrong password");
        assertThat(auth.getFailedMessage().getTimestamp()).isAfter("2022-03-01");
        assertThat(auth.getFailedMessage().getStatus()).isEqualTo(3);
    }

    @Tag("negative")
    @DisplayName("Should get 404 for incorrect resource")
    @Test
    void failedAuthIncorrectResource() {
        auth.login("user", "user", "/login1");

        assertThat(auth.getStatusCode()).isEqualTo(404);
        assertThat(auth.getFailedMessage().getError()).isEqualTo("Not Found");
        assertThat(auth.getFailedMessage().getMessage()).isEqualTo("Wrong resource");
        assertThat(auth.getFailedMessage().getTimestamp()).isAfter("2022-03-01");
        assertThat(auth.getFailedMessage().getStatus()).isEqualTo(3);
    }

    @Tag("negative")
    @DisplayName("Should get 500 for Internal Server Error")
    @Test
    void failedAuthInternalServerError() {
        auth.login("", "");

        assertThat(auth.getStatusCode()).isEqualTo(500);
        assertThat(auth.getFailedMessage().getError()).isEqualTo("Internal Server Error");
        assertThat(auth.getFailedMessage().getMessage()).isEqualTo("The app is failed");
        assertThat(auth.getFailedMessage().getTimestamp()).isAfter("2021-12-01");
        assertThat(auth.getFailedMessage().getStatus()).isEqualTo(4);
    }

}
