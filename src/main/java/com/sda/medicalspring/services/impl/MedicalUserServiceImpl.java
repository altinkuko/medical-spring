package com.sda.medicalspring.services.impl;

import com.sda.medicalspring.dtos.AuthRequest;
import com.sda.medicalspring.entities.MedicalUser;
import com.sda.medicalspring.entities.Role;
import com.sda.medicalspring.exceptions.GenericException;
import com.sda.medicalspring.repositories.RoleRepository;
import com.sda.medicalspring.repositories.UserRepository;
import com.sda.medicalspring.services.MedicalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MedicalUserServiceImpl implements MedicalUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public MedicalUser create(MedicalUser medicalUser, boolean isAdmin){
        if (medicalUser.getUserId() == null){
            if (!userRepository.existsByUsername(medicalUser.getUsername())){
                if (!isAdmin) {
                    Role role = roleRepository.findById("ROLE_EMPLOYEE").get();
                    medicalUser.setRole(role);
                } else {
                    Role role = roleRepository.findById("ROLE_ADMIN").get();
                    medicalUser.setRole(role);
                }
                medicalUser.setPassword(passwordEncoder.encode(medicalUser.getPassword()));
                medicalUser.setActive(true);
                return userRepository.save(medicalUser);
            } else {
                throw GenericException.usernameExists(medicalUser.getUsername());
            }
        } else {
            throw GenericException.idIsNotNull();
        }
    }
    @Override
    public ResponseEntity<?> login(AuthRequest authRequest){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(authentication.isAuthenticated());
        } catch (LockedException lockedException){
            return ResponseEntity.status(403).body(lockedException.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
