package com.sda.medicalspring.services;

import com.sda.medicalspring.dtos.AuthRequest;
import com.sda.medicalspring.entities.MedicalUser;
import org.springframework.http.ResponseEntity;

public interface MedicalUserService {
    MedicalUser create(MedicalUser medicalUser, boolean isAdmin);

    ResponseEntity<?> login(AuthRequest authRequest);
}
