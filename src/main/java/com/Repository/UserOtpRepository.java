package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Entity.UserOtp;

import java.util.Optional;

public interface UserOtpRepository extends JpaRepository<UserOtp, Long> {

    Optional<UserOtp> findByEmail(String email);
}