package com.example.university.rest.api.universitydata.advices;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * GlobalResponseHandler is a centralized handler that intercepts and modifies HTTP responses
 * before they are sent to the client.
 *
 * This class implements the ResponseBodyAdvice interface, enabling it to process and wrap
 * response bodies globally for all controllers.
 *
 * Use Case:
 * The primary purpose of this class is to standardize the structure of API responses by wrapping
 * the response body within an ApiResponse object. If the response body is already an instance of ApiResponse,
 * it remains unchanged. Otherwise, the response body is wrapped in a new ApiResponse instance.
 *
 * Methods:
 * - supports: Determines whether this advice is applicable to a specific controller method or response type.
 *             Always returns true, indicating applicability to all responses.
 * - beforeBodyWrite: Processes the response body before it is written to the HTTP response. If the body
 *                    is not an instance of ApiResponse, it wraps it in a new ApiResponse instance.
 */
@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType)
    {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response)
    {
        if(body instanceof ApiResponse<?>) {
            return body;
        }

        return new ApiResponse<>(body);
    }
}
