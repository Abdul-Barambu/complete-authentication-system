package com.abdul.reg_login_token_jwt.dto.dtoservice;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidation implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return true;
    }
}
