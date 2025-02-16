package com.example.university.rest.api.universitydata.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Represents the structure of an API error response used to encapsulate error details.
 *
 * The ApiError class is commonly utilized in exception handling mechanisms to provide
 * standardized and meaningful error messages to API clients. It supports detailed information
 * such as HTTP status, a descriptive error message, and a list of specific errors.
 *
 * Properties:
 * - httpStatus: Represents the HTTP status code related to the error (e.g., 404, 500).
 * - message: A human-readable description or summary of the error.
 * - errors: A list of specific error details, typically used to convey validation or domain-specific errors.
 *
 * This class uses a builder pattern provided by Lombok's @Builder annotation, allowing
 * for convenient creation of instances.
 */
@Builder
@Data
public class ApiError {
    private HttpStatus httpStatus;
    private String message;
    private List<String> errors;
}
