package com.pfa.microserviceusers.repository;

import com.pfa.microserviceusers.models.token.PasswordRestToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetPasswordRepository extends JpaRepository<PasswordRestToken, Long> {
    PasswordRestToken findByResetToken(String resetToken);
}
