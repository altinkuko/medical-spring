package com.sda.medicalspring.services.impl;

import com.sda.medicalspring.entities.Appointment;
import com.sda.medicalspring.entities.Report;
import com.sda.medicalspring.exceptions.GenericException;
import com.sda.medicalspring.repositories.AppointmentRepository;
import com.sda.medicalspring.repositories.ReportRepository;
import com.sda.medicalspring.services.ReportService;
import com.sda.medicalspring.static_data.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Report create(String description, Long appointmentId){
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        if (appointment.isPresent()){
            Report report = new Report();
            report.setDescription(description);
            report = reportRepository.save(report);
            Appointment present = appointment.get();
            present.setReport(report);
            present.setStatus(Status.COMPLETED);
            appointmentRepository.save(present);
            return report;
        } else {
            throw GenericException.idIsNotNull();
        }
    }
    @Override
    public Report update(Report report){
        if (report.getReportId() != null) {
            return reportRepository.save(report);
        } else
            throw GenericException.idIsNull();
    }
}
