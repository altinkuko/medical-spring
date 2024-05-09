package com.sda.medicalspring.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "medical_users")
@Data
public class MedicalUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(unique = true)
    private String username;
    private String password;
    private Boolean active;
    @ManyToOne
    @JoinColumn(name = "role")
    private Role role;
}
