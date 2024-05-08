package com.sda.medicalspring.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class DoctorDTO {
    private Long doctorId;
    @NotNull(message = "Username is null")
    private String username;
    @Length(min = 4, max = 20, message = "Name must be 4 - 20 characters")
    private String name;
    @NotBlank(message = "Specialization is required")
    private String specialization;
}
