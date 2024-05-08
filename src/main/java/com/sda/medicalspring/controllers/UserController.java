package com.sda.medicalspring.controllers;

import com.sda.medicalspring.dtos.AuthRequest;
import com.sda.medicalspring.entities.MedicalUser;
import com.sda.medicalspring.services.MedicalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private MedicalUserService userService;

    @PostMapping("/register")
    public MedicalUser register(@RequestBody MedicalUser medicalUser,
                                @RequestParam(required = false, defaultValue = "false") boolean isAdmin){
        return userService.create(medicalUser, isAdmin);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        return userService.login(authRequest);
    }
}
