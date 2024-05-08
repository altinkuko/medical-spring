package com.sda.medicalspring.exceptions;

import org.springframework.http.HttpStatus;

public class GenericException extends RuntimeException{
    private Integer status;
    private GenericException(String message, Integer status){
        super(message);
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }
    public static GenericException idIsNull(){
        String message = "Id must not be null";
        return new GenericException(message, 400);
    }
    public static GenericException idIsNotNull(){
        String message = "Id must be null";
        return new GenericException(message, 400);
    }
    public static GenericException notFound(Object id){
        String message = String.format("Record with id %s not found", id.toString());
        return new GenericException(message, 404);
    }

    public static GenericException changeDoctorException(Object appointmentId, Object doctorId){
        String message = String.format("Appointment with id %s not exists or Doctor with id %s not exists",
                appointmentId.toString(), doctorId.toString());
        return new GenericException(message, 404);
    }
    public static GenericException timeIsWrong(){
        String message = "Please select another time";
        return new GenericException(message, 400);
    }
    public static GenericException usernameExists(String username){
        String message = String.format("User with username %s exists", username);
        return new GenericException(message, 400);
    }
}
