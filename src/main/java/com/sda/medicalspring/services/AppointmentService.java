package com.sda.medicalspring.services;

import com.sda.medicalspring.entities.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment create(Appointment appointment, Long doctorId);

    Appointment update(Appointment appointment);

    Appointment changeDoctor(Long appointmentId, Long doctorId);

    Appointment cancelAppointment(Long appointmentId);

    List<Appointment> getAllForDoctor(Long doctorId);
}
