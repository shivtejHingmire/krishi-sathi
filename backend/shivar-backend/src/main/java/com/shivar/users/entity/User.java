package com.shivar.users.entity;

import com.shivar.entity.BaseEntity;
import com.shivar.farmer.entity.Farmer;
import com.shivar.users.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Represents the root identity of the SHIVAR application.
 * Every farmer, worker, or future admin account is based on this entity.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "mobile_number", nullable = false, unique = true, length = 15)
    private String mobileNumber;

    @Column(unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserStatus status = UserStatus.ACTIVE;

    @Column(name = "mobile_verified", nullable = false)
    private Boolean mobileVerified = false;

    @Column(name = "email_verified", nullable = false)
    private Boolean emailVerified = false;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    @OneToOne(mappedBy = "user")
    private Farmer farmer;
}