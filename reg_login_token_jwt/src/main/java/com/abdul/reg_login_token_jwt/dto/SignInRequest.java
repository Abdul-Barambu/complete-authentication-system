package com.abdul.reg_login_token_jwt.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
}
