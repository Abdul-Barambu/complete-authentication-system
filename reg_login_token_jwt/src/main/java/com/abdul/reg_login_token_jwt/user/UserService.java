package com.abdul.reg_login_token_jwt.user;

import com.abdul.reg_login_token_jwt.token.ConfirmationToken;
import com.abdul.reg_login_token_jwt.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
    }

    public ResponseEntity<Object> signUpUser(User user) {
        boolean emailExist = userRepository.findByEmail(user.getEmail()).isPresent();

        if (emailExist) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(emailExist("error", "Email already exist"));
        }

        String passwordEncoded = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);

        userRepository.save(user);

        String confirmationToken = UUID.randomUUID().toString();

        ConfirmationToken confirmToken = new ConfirmationToken(
                confirmationToken,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                user
        );

        confirmationTokenService.saveConfirmationToken(confirmToken);

        return ResponseEntity.ok().body(successRegister("success", "Registration successfully",
                "Confirm your token... " + confirmationToken));

    }

    public int enabledUser(String email){
        return userRepository.enableUser(email);
    }

    public Map<String, Object> emailExist(String status, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);

        Map<String, Object> data = new HashMap<>();
        data.put("message", message);

        response.put("data", data);
        return response;
    }

    public Map<String, Object> successRegister(String status, String message, String token) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);

        Map<String, Object> data = new HashMap<>();
        data.put("message", message);
        data.put("token", token);

        response.put("data", data);
        return response;
    }
}
