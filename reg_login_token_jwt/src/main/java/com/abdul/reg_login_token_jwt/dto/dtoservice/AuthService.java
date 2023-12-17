package com.abdul.reg_login_token_jwt.dto.dtoservice;

import com.abdul.reg_login_token_jwt.dto.JwtAuthenticationResponse;
import com.abdul.reg_login_token_jwt.dto.SignInRequest;

public interface AuthService {

    JwtAuthenticationResponse signin(SignInRequest signInRequest);
}
