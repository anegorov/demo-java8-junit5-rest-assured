package com.esn.backend.model;

import lombok.Data;
import java.util.Date;

@Data
public class AuthSuccess {
    private String login;
    private String token;
    private String refreshToken;
    private Date expiredAt;
}
