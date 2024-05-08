package com.sda.medicalspring.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reports")
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;
    private String description;
}
