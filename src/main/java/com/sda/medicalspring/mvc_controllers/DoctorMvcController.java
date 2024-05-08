package com.sda.medicalspring.mvc_controllers;

import com.sda.medicalspring.entities.Doctor;
import com.sda.medicalspring.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DoctorMvcController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/hello")
    public String helloWorld(Model model){
        model.addAttribute("message", "Hello Klasa");
        return "hello_world";
    }
    @GetMapping("/doctors")
    public String getAll(Model model){
        List<Doctor> doctors = doctorService.findAll();
        model.addAttribute("doctorList", doctors);
        return "doctors";
    }
}
