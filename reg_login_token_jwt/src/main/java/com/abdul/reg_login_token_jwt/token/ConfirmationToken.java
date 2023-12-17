package com.abdul.reg_login_token_jwt.token;

import com.abdul.reg_login_token_jwt.user.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ConfirmationToken {

    @Id
    @SequenceGenerator(name = "token_sequence", sequenceName = "token_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_sequence")
    private Long id;
    private String confirmationToken;
    private LocalDateTime createAt;
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ConfirmationToken(String confirmationToken,
                             LocalDateTime createAt,
                             LocalDateTime expiresAt,
                             User user) {
        this.confirmationToken = confirmationToken;
        this.createAt = createAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }
}
