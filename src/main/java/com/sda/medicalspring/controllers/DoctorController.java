package com.sda.medicalspring.controllers;

import com.sda.medicalspring.dtos.DoctorDTO;
import com.sda.medicalspring.entities.Doctor;
import com.sda.medicalspring.services.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/findAll")
    public List<Doctor> findAll() {
        return doctorService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Doctor findById(@PathVariable(name = "id") Long doctorId) {
        return doctorService.findById(doctorId);
    }

    @PostMapping("/create")
    public Doctor create(@Valid @RequestBody DoctorDTO doctor) {
        return doctorService.create(doctor);
    }
    @PutMapping("/update")
    public Doctor update(@Valid @RequestBody DoctorDTO doctor){
        return doctorService.update(doctor);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam Long doctorId){
        doctorService.delete(doctorId);
    }
    @GetMapping("/findBySpecialization")
    public List<Doctor> findBySpecialization(@RequestParam String specalization){
        return doctorService.findAllBySpecialization(specalization);
    }
}
