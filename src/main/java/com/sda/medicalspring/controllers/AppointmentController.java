package com.sda.medicalspring.controllers;

import com.sda.medicalspring.entities.Appointment;
import com.sda.medicalspring.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @PostMapping("/create")
    public Appointment create(@RequestBody Appointment appointment, @RequestParam Long doctorId){
        return appointmentService.create(appointment, doctorId);
    }
    @PutMapping("/update")
    public Appointment update(@RequestBody Appointment appointment){
        return appointmentService.update(appointment);
    }
    @PatchMapping("/changeDoctor")
    public Appointment changeDoctor(@RequestParam Long appointmentId, @RequestParam Long doctorId){
        return appointmentService.changeDoctor(appointmentId, doctorId);
    }
    @PatchMapping("/cancel")
    public Appointment cancel(@RequestParam Long appointmentId){
        return appointmentService.cancelAppointment(appointmentId);
    }
    @GetMapping("/byDoctor")
    public List<Appointment> getByDoctor(@RequestParam Long doctorId){
        return appointmentService.getAllForDoctor(doctorId);
    }

}
