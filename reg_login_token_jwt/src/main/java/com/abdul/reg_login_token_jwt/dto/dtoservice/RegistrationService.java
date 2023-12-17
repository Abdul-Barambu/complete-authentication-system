package com.abdul.reg_login_token_jwt.dto.dtoservice;

import com.abdul.reg_login_token_jwt.dto.RegistrationRequest;
import com.abdul.reg_login_token_jwt.token.ConfirmationToken;
import com.abdul.reg_login_token_jwt.token.ConfirmationTokenService;
import com.abdul.reg_login_token_jwt.user.Role;
import com.abdul.reg_login_token_jwt.user.User;
import com.abdul.reg_login_token_jwt.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidation emailValidation;
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;


    public ResponseEntity<Object> register(RegistrationRequest request) {

        return userService.signUpUser(
                new User(
                        request.getName(),
                        request.getEmail(),
                        request.getPassword(),
                        Role.USER
                )
        );
    }

    public ResponseEntity<Object> tokenConfirmation(String confirmationToken) {
        try {
            ConfirmationToken confirmToken = confirmationTokenService.getToken(confirmationToken).orElseThrow(() -> new IllegalStateException("Token Not Found"));

            if (confirmToken.getConfirmedAt() != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tokenAlreadyConfirmed("error", "Token Already confirmed"));
            }

            LocalDateTime expires = confirmToken.getExpiresAt();
            if (expires.isBefore(LocalDateTime.now())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tokenAlreadyExpired("error", "Token expired already"));
            }

            confirmationTokenService.setConfirmedAt(confirmationToken);
            userService.enabledUser(confirmToken.getUser().getEmail());

            return ResponseEntity.ok().body(successToken("success", "Your token has been confirmed successfully"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error("error", e.getMessage()));
        }
    }

    public Map<String, Object> tokenAlreadyConfirmed(String status, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);

        Map<String, Object> data = new HashMap<>();
        data.put("message", message);

        response.put("data", data);
        return response;
    }

    public Map<String, Object> tokenAlreadyExpired(String status, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);

        Map<String, Object> data = new HashMap<>();
        data.put("message", message);

        response.put("data", data);
        return response;
    }

    public Map<String, Object> successToken(String status, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);

        Map<String, Object> data = new HashMap<>();
        data.put("message", message);

        response.put("data", data);
        return response;
    }

    public Map<String, Object> error(String status, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);

        Map<String, Object> data = new HashMap<>();
        data.put("message", message);

        response.put("data", data);
        return response;
    }
}
