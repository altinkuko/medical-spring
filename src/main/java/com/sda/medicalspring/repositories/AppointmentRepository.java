package com.sda.medicalspring.repositories;

import com.sda.medicalspring.entities.Appointment;
import com.sda.medicalspring.static_data.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("select a from Appointment a where a.doctor.doctorId = :doctorId and a.start >= :now and a.status = :status")
    List<Appointment> findByDoctor(Long doctorId, LocalDateTime now, Status status);
}
