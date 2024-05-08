package com.sda.medicalspring.repositories;

import com.sda.medicalspring.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
