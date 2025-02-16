package com.example.university.rest.api.universitydata.exceptions;

/**
 * A custom exception that is thrown when a requested resource cannot be found.
 * This exception is typically used in scenarios where an entity or resource
 * does not exist in the system, or cannot be retrieved for various reasons.
 *
 * This exception extends {@link RuntimeException}, thus it is an unchecked exception.
 */
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message)
    {
        super(message);
    }

    public ResourceNotFoundException(String message,Throwable cause)
    {
        super(message,cause);    }

}
