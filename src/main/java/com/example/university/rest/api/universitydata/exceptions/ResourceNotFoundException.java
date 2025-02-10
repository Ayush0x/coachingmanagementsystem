package com.example.university.rest.api.universitydata.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message)
    {
        super(message);
    }

    public ResourceNotFoundException(String message,Throwable cause)
    {
        super(message,cause);    }

}
