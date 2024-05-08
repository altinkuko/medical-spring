package com.sda.medicalspring.example_tests;

import com.sda.medicalspring.entities.MedicalUser;
import com.sda.medicalspring.exceptions.GenericException;
import com.sda.medicalspring.mock_user_service.MockUserService;
import com.sda.medicalspring.repositories.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MockUserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private MockUserService mockUserService;

    private static MedicalUser medicalUser;

    @BeforeAll
    static void createMockedUser(){
        medicalUser = new MedicalUser();
        medicalUser.setUsername("admin");
        medicalUser.setActive(false);
    }
    @Test
    void shouldBeNullByDefault(){
        assertNull(mockUserService.findById(2L));
    }

    @Test
    void shouldFindById(){
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(new MedicalUser()));
        assertNotNull(mockUserService.findById(1L));
    }
    @Test
    void shouldThrowNotFoundException(){
        Mockito.when(userRepository.findById(1L)).thenThrow(GenericException.notFound(1L));
        assertThrows(GenericException.class,()-> mockUserService.findById(1L));
    }
    @Test
    void shouldThrowExceptionByDefault(){
        assertThrows(GenericException.class, ()-> mockUserService.findByUsername("admin"));
    }
    @Test
    void shouldFindByUsername(){
        Mockito.when(userRepository.findByUsername("admin")).thenReturn(Optional.of(medicalUser));
        assertNotNull(mockUserService.findByUsername("admin"));
        assertFalse(mockUserService.findByUsername("admin").getActive());
    }
}
