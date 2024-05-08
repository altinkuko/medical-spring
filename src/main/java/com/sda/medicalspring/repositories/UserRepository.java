package com.sda.medicalspring.repositories;

import com.sda.medicalspring.entities.MedicalUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MedicalUser, Long> {
    Optional<MedicalUser> findByUsername(String username);
    Boolean existsByUsername(String username);
}
