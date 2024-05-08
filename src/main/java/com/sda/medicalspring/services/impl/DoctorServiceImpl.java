package com.sda.medicalspring.services.impl;

import com.sda.medicalspring.dtos.DoctorDTO;
import com.sda.medicalspring.dtos.DoctorMapper;
import com.sda.medicalspring.entities.Doctor;
import com.sda.medicalspring.exceptions.GenericException;
import com.sda.medicalspring.repositories.DoctorRepository;
import com.sda.medicalspring.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorMapper doctorMapper;

    @Override
    public Doctor create(DoctorDTO doctorDTO){
        if (doctorDTO.getDoctorId() == null) {
            Doctor doctor = doctorMapper.toEntity(doctorDTO);
            return doctorRepository.save(doctor);
        } else {
            throw GenericException.idIsNotNull();
        }
    }
    @Override
    public Doctor update(DoctorDTO doctorDTO){
        if (doctorDTO.getDoctorId() != null) {
            Doctor doctor = doctorMapper.toEntity(doctorDTO);
            return doctorRepository.save(doctor);
        } else {
            throw GenericException.idIsNull();
        }
    }
    @Override
    public List<Doctor> findAll(){
        return doctorRepository.findAll();
    }
    @Override
    public Doctor findById(Long id){
        return doctorRepository.findById(id).orElseThrow(()-> GenericException.notFound(id));
    }
    @Override
    public void delete(Long id){
        doctorRepository.deleteById(id);
    }
    @Override
    public List<Doctor> findAllBySpecialization(String specialization){
        return doctorRepository.findBySpecialization(specialization);
    }
}
