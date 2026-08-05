package com.alumniconnect.repository;

import com.alumniconnect.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.alumniconnect.enums.Role;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    long countByRole(Role role);

}

