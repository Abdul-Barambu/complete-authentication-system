package com.abdul.reg_login_token_jwt.user;

import com.abdul.reg_login_token_jwt.dto.JwtAuthenticationResponse;
import com.abdul.reg_login_token_jwt.dto.RegistrationRequest;
import com.abdul.reg_login_token_jwt.dto.SignInRequest;
import com.abdul.reg_login_token_jwt.dto.dtoservice.AuthService;
import com.abdul.reg_login_token_jwt.dto.dtoservice.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/auth")
@AllArgsConstructor
public class UserController {

    private final RegistrationService registrationService;
    private final AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<Object> register(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(registrationService.register(request));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody SignInRequest signInRequest) {
    return ResponseEntity.ok(authService.signin(signInRequest));
    }

    @GetMapping(path = "/confirm")
    public ResponseEntity<Object> confirmToken(@RequestParam("confirmToken") String confirmationToken) {
        return ResponseEntity.ok(registrationService.tokenConfirmation(confirmationToken));
    }
}
