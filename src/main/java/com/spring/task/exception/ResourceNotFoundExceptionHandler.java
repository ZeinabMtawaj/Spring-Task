package com.spring.task.exception;

import com.spring.task.common.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ResourceNotFoundExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {

        return new ResponseEntity<>(
                Response.builder()
                        .timeStamp(now())
                        .status(NOT_FOUND)
                        .statusCode(NOT_FOUND.value())
                        .message(ex.getMessage())
//                        .developerMessage("Detailed explanation about the error.")
                        .build(),
                NOT_FOUND
        );


    }
}

