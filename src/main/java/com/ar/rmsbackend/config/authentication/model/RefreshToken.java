package com.ar.rmsbackend.config.authentication.model;

import com.ar.rmsbackend.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Builder
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

//    @Column(name = "user_id", insertable = false, updatable = false)
//    private Long userId;

    @NotBlank
    private String refreshToken;

    @NotNull
    private Instant expireTime;

}
