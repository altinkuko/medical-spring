package com.sda.medicalspring.entities;

import com.sda.medicalspring.static_data.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointmets")
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long appointmentId;
    @Column(name = "patient_name")
    private String patientName;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @Column(name = "start_time")
    private LocalDateTime start;
    @Column(name = "end_time")
    private LocalDateTime end;
    private String note;
    @ManyToOne
    @JoinColumn(name = "doctor")
    private Doctor doctor;
    @OneToOne
    @JoinColumn(name = "report")
    private Report report;
}
