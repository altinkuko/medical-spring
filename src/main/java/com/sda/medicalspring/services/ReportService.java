package com.sda.medicalspring.services;

import com.sda.medicalspring.entities.Report;

public interface ReportService {
    Report create(String description, Long appointmentId);

    Report update(Report report);
}
