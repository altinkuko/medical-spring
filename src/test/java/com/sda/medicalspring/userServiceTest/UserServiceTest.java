package com.sda.medicalspring.userServiceTest;

import com.sda.medicalspring.dtos.AuthRequest;
import com.sda.medicalspring.entities.MedicalUser;
import com.sda.medicalspring.exceptions.GenericException;
import com.sda.medicalspring.repositories.UserRepository;
import com.sda.medicalspring.services.MedicalUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

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

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/users.csv", numLinesToSkip = 1, delimiter = ';')
    void shouldCreateUserFromCsvFile(String username, String password, Boolean active, Boolean isAdmin){
        MedicalUser medicalUser = new MedicalUser();
        medicalUser.setUsername(username);
        medicalUser.setPassword(password);
        medicalUser.setActive(active);
        medicalUserService.create(medicalUser, isAdmin);
        assertNotNull(medicalUser.getUserId());
        assertTrue(medicalUser.getActive());
        assertEquals("testUser", medicalUser.getUsername());
    }

    @ParameterizedTest
    @CsvSource(value = {"admin;admin", "test;test"}, delimiter = ';')
    void shouldThrowException(String username, String password){
        MedicalUser medicalUser = new MedicalUser();
        medicalUser.setUsername(username);
        medicalUser.setPassword(password);
        assertThrows(GenericException.class, ()-> medicalUserService.create(medicalUser, false));
    }

    @ParameterizedTest
    @CsvSource(value = {"admin;admin", "test;test"}, delimiter = ';')
    void shouldLogin(String username, String password){
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername(username);
        authRequest.setPassword(password);
        assertEquals(HttpStatus.OK, medicalUserService.login(authRequest).getStatusCode());
        assertNotEquals(Boolean.FALSE, medicalUserService.login(authRequest).getBody());
    }
    @ParameterizedTest
    @CsvSource(value = {"admin;test"}, delimiter = ';')
    void shouldNotLogin(String username, String password){
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername(username);
        authRequest.setPassword(password);
        assertEquals(HttpStatus.UNAUTHORIZED, medicalUserService.login(authRequest).getStatusCode());
        assertEquals("Bad credentials", medicalUserService.login(authRequest).getBody());
    }
}
