package com.example.university.rest.api.universitydata.advices;

import com.example.university.rest.api.universitydata.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Data
@RestControllerAdvice
//@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getHttpStatus());
    }

@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(ResourceNotFoundException e)
    {
        ApiError error=ApiError.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .build();
        return buildErrorResponseEntity(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntimeException(RuntimeException exception)
    {
        ApiError error=ApiError.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(error);
    }
}
