package com.abdul.reg_login_token_jwt.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class RegistrationRequest {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}
