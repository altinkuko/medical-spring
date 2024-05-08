package com.sda.medicalspring;

import com.sda.medicalspring.services.DoctorService;
import com.sda.medicalspring.services.impl.DoctorServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;

@SpringBootApplication
public class MedicalSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalSpringApplication.class, args);
	}

}
