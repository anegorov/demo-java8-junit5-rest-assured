package com.esn.backend.model;

import lombok.Data;
import java.util.Date;

@Data
public class AuthFail {
    private String error;
    private String message;
    private Date timestamp;
    private int status;
}
