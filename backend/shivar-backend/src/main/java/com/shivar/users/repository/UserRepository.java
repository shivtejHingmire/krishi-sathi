package com.shivar.users.repository;

import com.shivar.users.entity.User;
import com.shivar.users.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for performing database operations on User entities.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByMobileNumber(String mobileNumber);

    Optional<User> findByEmail(String email);

    boolean existsByMobileNumber(String mobileNumber);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByMobileNumberAndIdNot(String mobileNumber, Long id);

    List<User> findByFullNameContainingIgnoreCase(String fullName);

    List<User> findByEmailContainingIgnoreCase(String email);

    List<User> findByMobileNumberContaining(String mobileNumber);

    List<User> findByStatus(UserStatus status);

}