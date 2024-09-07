package com.codingshuttle.TestingApp.advices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Handles and wraps all responses in a standardized ApiResponse format.
 */
@ControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    private static final Logger logger = LoggerFactory.getLogger(GlobalResponseHandler.class);
    private static final String SWAGGER_DOCS_PATH = "/v3/api-docs";

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // Log the method being checked
        logger.debug("Checking if ResponseBodyAdvice supports: {}", returnType.getMethod().getName());

        // Always wrap responses except for Swagger documentation requests
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        // Log the response body being processed
        logger.debug("Wrapping response body: {}", body);

        // Check if the request path matches the Swagger documentation endpoint
        String requestPath = request.getURI().getPath();
        if (requestPath.startsWith(SWAGGER_DOCS_PATH)) {
            return body; // Do not wrap responses for Swagger API documentation
        }

        // Wrap response in ApiResponse format
        if (body instanceof ApiError) {
            return new ApiResponse<>((ApiError) body);
        } else {
            return new ApiResponse<>(body);
        }
    }
}
