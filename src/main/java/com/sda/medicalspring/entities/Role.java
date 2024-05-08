package com.sda.medicalspring.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    private String role;
}
