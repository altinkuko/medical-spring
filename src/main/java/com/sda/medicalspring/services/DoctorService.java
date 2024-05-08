package com.sda.medicalspring.services;

import com.sda.medicalspring.dtos.DoctorDTO;
import com.sda.medicalspring.entities.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor create(DoctorDTO doctor);

    Doctor update(DoctorDTO doctor);

    List<Doctor> findAll();

    Doctor findById(Long id);

    void delete(Long id);

    List<Doctor> findAllBySpecialization(String specialization);
}
