package com.sda.medicalspring.security;

import com.sda.medicalspring.entities.MedicalUser;
import com.sda.medicalspring.exceptions.GenericException;
import com.sda.medicalspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MedicalUser> user = userRepository.findByUsername(username);
        if (user.isPresent()){
            return new UserDetailImpl(user.get());
        } else {
            throw GenericException.notFound(username);
        }
    }
}
