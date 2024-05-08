package com.sda.medicalspring.repositories;

import com.sda.medicalspring.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findAllBySpecializationIgnoreCase(String specialization);
    @Query("select d from Doctor d where upper(d.specialization) = upper(:specialization)")
    List<Doctor> findBySpecialization(@Param(value = "specialization") String specialization);
}
