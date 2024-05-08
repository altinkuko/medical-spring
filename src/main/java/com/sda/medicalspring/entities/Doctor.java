package com.sda.medicalspring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "doctors")
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long doctorId;
    @Column(unique = true, nullable = false)
    private String username;
    private String name;
    private String specialization;
    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<Appointment> appointments;
}
