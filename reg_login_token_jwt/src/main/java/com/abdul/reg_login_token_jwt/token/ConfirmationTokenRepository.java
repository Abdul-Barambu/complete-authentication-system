package com.abdul.reg_login_token_jwt.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByConfirmationToken(String confirmationToken);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken ct SET ct.confirmedAt = ?2 WHERE ct.confirmationToken = ?1")
        int updateConfirmedAt(String confirmationToken, LocalDateTime confirmedAt);
}
