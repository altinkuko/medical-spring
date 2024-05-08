package com.sda.medicalspring.services.impl;

import com.sda.medicalspring.entities.Appointment;
import com.sda.medicalspring.entities.Doctor;
import com.sda.medicalspring.exceptions.GenericException;
import com.sda.medicalspring.repositories.AppointmentRepository;
import com.sda.medicalspring.repositories.DoctorRepository;
import com.sda.medicalspring.services.AppointmentService;
import com.sda.medicalspring.static_data.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Appointment create(Appointment appointment, Long doctorId) {
        if (appointment.getAppointmentId() == null && doctorRepository.existsById(doctorId)) {
            List<Appointment> appointments = getAllForDoctor(doctorId);
            AtomicBoolean result = new AtomicBoolean(false);
            appointments.forEach(existingAppointment -> {
                if (isTimeWrong(existingAppointment, appointment.getStart(), appointment.getEnd())){
                    result.set(true);
                }
            });
            if (!result.get()) {
                Doctor doctor = doctorRepository.findById(doctorId).get();
                appointment.setDoctor(doctor);
                appointment.setStatus(Status.CREATED);
                return appointmentRepository.save(appointment);
            } else {
                throw GenericException.timeIsWrong();
            }
        } else {
            throw GenericException.idIsNotNull();
        }
    }

    @Override
    public Appointment update(Appointment appointment) {
        if (appointment.getAppointmentId() != null) {
            //TODO bug fix
            return appointmentRepository.save(appointment);
        } else {
            throw GenericException.idIsNull();
        }
    }

    @Override
    public Appointment changeDoctor(Long appointmentId, Long doctorId) {
        if (appointmentRepository.existsById(appointmentId)
                && doctorRepository.existsById(doctorId)) {
            Appointment appointment = appointmentRepository.findById(appointmentId).get();
            Doctor doctor = doctorRepository.findById(doctorId).get();
            appointment.setDoctor(doctor);
            return appointmentRepository.save(appointment);
        } else {
            throw GenericException.changeDoctorException(appointmentId, doctorId);
        }
    }
    @Override
    public Appointment cancelAppointment(Long appointmentId){
        if (appointmentRepository.existsById(appointmentId)){
            Appointment appointment = appointmentRepository.findById(appointmentId).get();
            appointment.setStatus(Status.CANCELED);
            return appointmentRepository.save(appointment);
        } else {
            throw GenericException.notFound(appointmentId);
        }
    }
    @Override
    public List<Appointment> getAllForDoctor(Long doctorId){
        return appointmentRepository.findByDoctor(doctorId, LocalDateTime.now(), Status.CREATED);
    }

    private Boolean isTimeWrong(Appointment appointment, LocalDateTime start, LocalDateTime end) {
        return ((start.isAfter(appointment.getStart()) && start.isBefore(appointment.getEnd()))
                || (end.isAfter(appointment.getStart()) && end.isBefore(appointment.getEnd()))
                || (start.isBefore(appointment.getStart()) && end.isAfter(appointment.getEnd()))
                || (start.equals(appointment.getStart()) || end.equals(appointment.getEnd()))
                || start.isAfter(end));
    }
}
