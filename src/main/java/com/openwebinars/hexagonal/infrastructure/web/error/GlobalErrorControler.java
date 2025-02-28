package com.openwebinars.hexagonal.infrastructure.web.error;

import com.openwebinars.hexagonal.domain.error.DomainEntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class GlobalErrorControler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DomainEntityNotFoundException.class)
     ProblemDetail handlerEntityNotFoundException(DomainEntityNotFoundException ex){
        ProblemDetail  problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Not found");
        problemDetail.setType(URI.create("http://www.openwebinars.net/error/not-found"));

        return problemDetail;
    }
}
