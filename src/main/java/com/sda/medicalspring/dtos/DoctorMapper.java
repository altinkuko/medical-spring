package com.sda.medicalspring.dtos;

import com.sda.medicalspring.entities.Doctor;
import org.springframework.stereotype.Service;

@Service
public class DoctorMapper {
    public Doctor toEntity(DoctorDTO doctorDTO){
        Doctor doctor = new Doctor();
        doctor.setDoctorId(doctor.getDoctorId());
        doctor.setName(doctorDTO.getName());
        doctor.setUsername(doctorDTO.getUsername());
        doctor.setSpecialization(doctorDTO.getSpecialization());
        return doctor;
    }
    public DoctorDTO toDTO(Doctor doctor){
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setDoctorId(doctor.getDoctorId());
        doctorDTO.setName(doctor.getName());
        doctorDTO.setSpecialization(doctor.getSpecialization());
        doctorDTO.setUsername(doctor.getUsername());
        return doctorDTO;
    }
}
