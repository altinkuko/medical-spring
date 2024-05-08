package com.sda.medicalspring.mock_user_service;

import com.sda.medicalspring.entities.MedicalUser;
import com.sda.medicalspring.exceptions.GenericException;
import com.sda.medicalspring.repositories.UserRepository;

public class MockUserService {
    private final UserRepository userRepository;

    public MockUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MedicalUser findById(Long id){
        return userRepository.findById(id).orElse(null);
    }
    public MedicalUser findByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(()-> GenericException.notFound(username));
    }
}
