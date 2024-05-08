package com.sda.medicalspring.userServiceTest;

import com.sda.medicalspring.entities.MedicalUser;
import com.sda.medicalspring.repositories.UserRepository;
import com.sda.medicalspring.services.MedicalUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private MedicalUserService medicalUserService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldCreateUser(){
        MedicalUser medicalUser = new MedicalUser();
        medicalUser.setUsername("user");
        medicalUser.setPassword("user");
        medicalUser.setActive(true);
        medicalUserService.create(medicalUser, false);
        assertNotNull(medicalUser.getUserId());
    }
    @ParameterizedTest
    @ValueSource(longs = {1L, 2L})
    void shouldFindById(Long id){
       assertTrue(userRepository.existsById(id));
    }
    @ParameterizedTest
    @ValueSource(strings = {"admin", "test"})
    void shouldFindByUsername(String username){
        assertTrue(userRepository.findByUsername(username).isPresent());
    }
}
