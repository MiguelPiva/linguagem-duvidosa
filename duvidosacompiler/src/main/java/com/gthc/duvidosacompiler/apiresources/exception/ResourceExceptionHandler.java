package com.gthc.duvidosacompiler.apiresources.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gthc.duvidosacompiler.core.exceptions.DuvidosaSemanticException;

import jakarta.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(DuvidosaSemanticException.class)
    public ResponseEntity<StandardError> objectNotFound(DuvidosaSemanticException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Erro durante a compilação", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
