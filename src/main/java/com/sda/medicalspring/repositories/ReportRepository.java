package com.sda.medicalspring.repositories;

import com.sda.medicalspring.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
