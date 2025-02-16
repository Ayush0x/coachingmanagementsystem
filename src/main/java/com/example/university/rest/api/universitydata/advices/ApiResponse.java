package com.example.university.rest.api.universitydata.advices;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Represents a standardized API response structure for returning data or errors in a consistent format.
 *
 * The ApiResponse class contains the following properties:
 * - timeStamp: Captures the time at which the response is generated.
 * - data: Represents the payload or result of the API request. It is generic and can hold any type.
 * - apiError: Holds error details when the API request fails.
 *
 * This class provides three constructors:
 * - A default constructor that initializes the timeStamp with the current time.
 * - A parameterized constructor for creating a successful response with a data payload.
 * - A parameterized constructor for creating an error response with an ApiError object.
 *
 * This class is commonly used in conjunction with exception handling and response wrapping
 * mechanisms, ensuring consistent data structure for API responses.
 *
 * @param <T> The type of the data payload included in the response.
 */
@Data
public class ApiResponse<T> {
    private LocalDateTime timeStamp;
    private T data;
    private ApiError apiError;

    public ApiResponse()
    {
        this.timeStamp=LocalDateTime.now();
    }
    public ApiResponse(T data)
    {
        this();
        this.data=data;
    }
    public ApiResponse(ApiError error)
    {
        this();
        this.apiError=error;
    }
}
