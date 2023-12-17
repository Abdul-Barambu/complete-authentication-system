package com.abdul.reg_login_token_jwt.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken confirmationToken){
        confirmationTokenRepository.save(confirmationToken);
    }

    public Optional<ConfirmationToken> getToken(String confirmationToken){
        return confirmationTokenRepository.findByConfirmationToken(confirmationToken);
    }

    public int setConfirmedAt(String confirmationToken){
        return confirmationTokenRepository.updateConfirmedAt(confirmationToken, LocalDateTime.now());
    }
}
